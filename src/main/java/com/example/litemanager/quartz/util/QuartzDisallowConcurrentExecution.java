package com.example.litemanager.quartz.util;

import com.example.litemanager.domain.Task;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（禁止并发执行）
 * 
 *
 *
 */
@DisallowConcurrentExecution
public class QuartzDisallowConcurrentExecution extends AbstractQuartzJob
{
    @Override
    protected Object doExecute(JobExecutionContext context, Task sysJob) throws Exception
    {
       return JobInvokeUtil.invokeMethod(sysJob);
    }
}
