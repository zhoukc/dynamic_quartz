package club.javaprod.quartz.dynamic_quartz.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class TestJob2 extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        System.out.println("第二个定时任务开始执行");

        System.out.println(jobExecutionContext.getTrigger().getJobKey().getName());

        System.out.println("第二个定时任务结束执行");

    }
}
