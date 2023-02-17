package com.example.litemanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.litemanager.domain.Task;
import com.example.litemanager.quartz.constant.ScheduleConstants;
import com.example.litemanager.quartz.util.CronUtils;
import com.example.litemanager.quartz.util.ScheduleUtils;
import com.example.litemanager.service.TaskService;
import com.example.litemanager.mapper.TaskMapper;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author hejie
 * @description 针对表【task】的数据库操作Service实现
 * @createDate 2023-02-17 14:15:40
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task>
        implements TaskService {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private TaskMapper taskMapper;

    /**
     * 项目启动时，初始化定时器 主要是防止手动修改数据库导致未同步到定时任务处理（注：不能手动修改数据库ID和任务组名，否则会导致脏数据）
     */
    @PostConstruct
    public void init() throws SchedulerException {
        scheduler.clear();
        List<Task> jobList = this.list();
        for (Task job : jobList) {
            ScheduleUtils.createScheduleJob(scheduler, job);
        }
    }


    @Override
    public IPage<Task> selectTaskList(Task job, int pageNum, int pageSize) {
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(job.getTaskName())) {
            queryWrapper.like("task_name", "%" + job.getTaskName() + "%");
        }
        if (StringUtils.isNotBlank(job.getTaskGroup())) {
            queryWrapper.eq("task_group", job.getTaskGroup());
        }
        if (StringUtils.isNotBlank(job.getStatus())) {
            queryWrapper.eq("status", job.getStatus());
        }
        if (StringUtils.isNotBlank(job.getInvokeTarget())) {
            queryWrapper.like("invoke_target", "%" + job.getInvokeTarget() + "%");
        }
        Page<Task> page = this.page(new Page<>(pageNum, pageSize), queryWrapper);
        return page;
    }

    @Override
    public Task selectJobById(Long jobId) {

        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", jobId);
        return this.getOne(queryWrapper);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int pauseJob(Task job) throws SchedulerException {
        Long jobId = job.getId();
        String jobGroup = job.getTaskGroup();
        job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
        int rows = taskMapper.updateTask(job);
        if (rows > 0) {
            scheduler.pauseJob(ScheduleUtils.getJobKey(jobId, jobGroup));
        }
        return rows;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int resumeJob(Task job) throws SchedulerException {
        Long jobId = job.getId();
        String jobGroup = job.getTaskGroup();
        job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
        int rows = taskMapper.updateTask(job);
        if (rows > 0) {
            scheduler.resumeJob(ScheduleUtils.getJobKey(jobId, jobGroup));
        }
        return rows;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteJob(Task job) throws SchedulerException {
        Long jobId = job.getId();
        String jobGroup = job.getTaskGroup();
        int rows = taskMapper.deleteTaskById(jobId);
        if (rows > 0) {
            scheduler.deleteJob(ScheduleUtils.getJobKey(jobId, jobGroup));
        }
        return rows;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteJobByIds(Long[] jobIds) throws SchedulerException {
        for (Long jobId : jobIds) {
            Task job = taskMapper.selectTaskById(jobId);
            deleteJob(job);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int changeStatus(Task job) throws SchedulerException {
        int rows = 0;
        String status = job.getStatus();
        if (ScheduleConstants.Status.NORMAL.getValue().equals(status)) {
            rows = resumeJob(job);
        } else if (ScheduleConstants.Status.PAUSE.getValue().equals(status)) {
            rows = pauseJob(job);
        }
        return rows;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean run(Task job) throws SchedulerException {
        boolean result = false;
        Long jobId = job.getId();
        Task properties = selectJobById(job.getId());
        String jobGroup = properties.getTaskGroup();
        // 参数
        JobDataMap dataMap = new JobDataMap();
        dataMap.put(ScheduleConstants.TASK_PROPERTIES, properties);
        JobKey jobKey = ScheduleUtils.getJobKey(jobId, jobGroup);
        if (scheduler.checkExists(jobKey)) {
            result = true;
            scheduler.triggerJob(jobKey, dataMap);
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertJob(Task job) throws SchedulerException {
        job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
        int rows = taskMapper.insertJob(job);
        if (rows > 0) {
            ScheduleUtils.createScheduleJob(scheduler, job);
        }
        return rows;
    }

    @Override
    public int updateJob(Task job) throws SchedulerException {
        Task properties = selectJobById(job.getId());
        int rows = taskMapper.updateJob(job);
        if (rows > 0) {
            updateSchedulerJob(job, properties.getTaskGroup());
        }
        return rows;
    }


    /**
     * 更新任务
     *
     * @param job      任务对象
     * @param jobGroup 任务组名
     */
    public void updateSchedulerJob(Task job, String jobGroup) throws SchedulerException {
        Long jobId = job.getId();
        // 判断是否存在
        JobKey jobKey = ScheduleUtils.getJobKey(jobId, jobGroup);
        if (scheduler.checkExists(jobKey)) {
            // 防止创建时存在数据问题 先移除，然后在执行创建操作
            scheduler.deleteJob(jobKey);
        }
        ScheduleUtils.createScheduleJob(scheduler, job);
    }


    @Override
    public boolean checkCronExpressionIsValid(String cronExpression) {
        return CronUtils.isValid(cronExpression);
    }
}




