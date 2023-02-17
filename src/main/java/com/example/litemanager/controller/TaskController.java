package com.example.litemanager.controller;

import com.example.litemanager.conmon.ReturnMessage;
import com.example.litemanager.domain.Task;
import com.example.litemanager.service.TaskService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 任务管理
 * @author: hj
 * @date: 2023/2/17
 * @time: 3:39 PM
 */
@RestController
public class TaskController {


    @Autowired
    private TaskService taskService;

    /**
     * 任务列表
     *
     * @param pageNum
     * @param pageSize
     * @param task
     * @return
     */
    @GetMapping("/task/lis")
    public ReturnMessage taskList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                  @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                  Task task) {
        return ReturnMessage.success(taskService.selectTaskList(task, pageNum, pageSize));
    }

    @GetMapping("/{taskId}")
    public ReturnMessage getTask(@RequestParam("taskId") Long taskId) {
        return ReturnMessage.success(taskService.selectJobById(taskId));
    }

    @PostMapping("/task/add")
    public ReturnMessage addTask(Task task) throws SchedulerException {
        if (taskService.checkCronExpressionIsValid(task.getCronExpression())) {
            return ReturnMessage.error("cron表达式不正确");
        }
        return ReturnMessage.success(taskService.insertJob(task));
    }


    @PostMapping("/task/update")
    public ReturnMessage updateTask(Task task) throws SchedulerException {
        if (taskService.checkCronExpressionIsValid(task.getCronExpression())) {
            return ReturnMessage.error("cron表达式不正确");
        }
        return ReturnMessage.success(taskService.updateJob(task));
    }

    @PutMapping("/task/changeStatus")
    public ReturnMessage changeStatus(@RequestBody Task task) throws SchedulerException {
        Task newJob = taskService.selectJobById(task.getId());
        newJob.setStatus(task.getStatus());
        return ReturnMessage.success(taskService.changeStatus(newJob));
    }


    @PutMapping("/task/run")
    public ReturnMessage run(@RequestBody Task task) throws SchedulerException {
        boolean result = taskService.run(task);
        return result ? ReturnMessage.success(null) : ReturnMessage.error("任务不存在或已过期");
    }

    @DeleteMapping("/{jobIds}")
    public ReturnMessage remove(@PathVariable Long[] jobIds) throws SchedulerException {
        taskService.deleteJobByIds(jobIds);
        return ReturnMessage.success(null);
    }

}
