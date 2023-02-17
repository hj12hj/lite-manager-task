package com.example.litemanager.quartz.util;

import com.example.litemanager.domain.Task;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（允许并发执行）
 * 
 * @author ruoyi
 *
 */
public class QuartzJobExecution extends AbstractQuartzJob
{
    @Override
    protected Object doExecute(JobExecutionContext context, Task sysJob) throws Exception
    {
       return JobInvokeUtil.invokeMethod(sysJob);
    }
}
