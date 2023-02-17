package com.example.litemanager.quartz.util;

import cn.hutool.extra.spring.SpringUtil;
import com.example.litemanager.domain.Task;
import com.example.litemanager.domain.TaskResult;
import com.example.litemanager.quartz.constant.ScheduleConstants;
import com.example.litemanager.service.TaskResultService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * 抽象quartz调用
 *
 *
 */
public abstract class AbstractQuartzJob implements Job {
    private static final Logger log = LoggerFactory.getLogger(AbstractQuartzJob.class);

    /**
     * 线程本地变量
     */
    private static ThreadLocal<Date> threadLocal = new ThreadLocal<>();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Task sysJob = (Task) context.getMergedJobDataMap().get(ScheduleConstants.TASK_PROPERTIES);
//        BeanUtils.copyProperties(sysJob, context.getMergedJobDataMap().get(ScheduleConstants.TASK_PROPERTIES));
        String res = "";
        try {
            before(context, sysJob);
            if (sysJob != null) {
                res = (String) doExecute(context, sysJob);
            }
            after(context, sysJob, null, res);
        } catch (Exception e) {
            log.error("任务执行异常  - ：", e);
            after(context, sysJob, e,res);
        }
    }

    /**
     * 执行前
     *
     * @param context 工作执行上下文对象
     * @param sysJob  系统计划任务
     */
    protected void before(JobExecutionContext context, Task sysJob) {
        threadLocal.set(new Date());
    }

    /**
     * 执行后
     *
     * @param context 工作执行上下文对象
     * @param task    系统计划任务
     */
    protected void after(JobExecutionContext context, Task task, Exception e, String res) {
        Date startTime = threadLocal.get();
        threadLocal.remove();
        TaskResult taskResult = new TaskResult();
        taskResult.setShellFileName(task.getTaskName());
        taskResult.setExecuteTime(new Date());
        taskResult.setVersion(0);
        taskResult.setLastUpdateUser(0);
        taskResult.setLastUpdateTime(new Date());
        if (e != null) {
            taskResult.setJavaExecuteResult("执行失败:   " + e.getMessage());
            taskResult.setShellExecuteResult(res);
        } else {
            taskResult.setJavaExecuteResult("执行成功");
            taskResult.setShellExecuteResult(res);
        }
        SpringUtil.getBean(TaskResultService.class).save(taskResult);
    }

    /**
     * 执行方法，由子类重载
     *
     * @param context 工作执行上下文对象
     * @param sysJob  系统计划任务
     * @throws Exception 执行过程中的异常
     */
    protected abstract Object doExecute(JobExecutionContext context, Task sysJob) throws Exception;
}
