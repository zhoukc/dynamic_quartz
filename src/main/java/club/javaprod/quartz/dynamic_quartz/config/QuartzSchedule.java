package club.javaprod.quartz.dynamic_quartz.config;

import club.javaprod.quartz.dynamic_quartz.jobs.TestJob;
import club.javaprod.quartz.dynamic_quartz.jobs.TestJob2;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzSchedule {

    @Bean
    public JobDetail testJob() {
        return JobBuilder.newJob(TestJob.class).withIdentity("job1", "group1")//任务分组
                .withDescription("第一个定时任务详情").storeDurably().build();
    }

    @Bean
    public Trigger testJobTrigger() {
        //设置默认cron表达式，后面通过接口动态修改控制 每隔10秒触发一次
        CronScheduleBuilder schedule = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");
        CronTrigger trigger = TriggerBuilder.newTrigger().forJob(testJob()).withIdentity("job1", "group1")//触发器分组
                .withSchedule(schedule).build();
        return trigger;
    }


    @Bean
    public JobDetail testJob2() {
        return JobBuilder.newJob(TestJob2.class).withIdentity("job2", "group2")//任务分组
                .withDescription("第二个定时任务详情").storeDurably().build();
    }

    @Bean
    public Trigger testJob2Trigger() {
        //设置默认cron表达式，后面通过接口动态修改控制 每隔20秒触发一次
        CronScheduleBuilder schedule = CronScheduleBuilder.cronSchedule("0/20 * * * * ?");
        CronTrigger trigger = TriggerBuilder.newTrigger().forJob(testJob2()).withIdentity("job2", "group2")//触发器分组和任务详情同名方便修改触发器
                .withSchedule(schedule).build();
        return trigger;
    }

    //更多高级操作请见官方文档 http://www.quartz-scheduler.org/documentation

}
