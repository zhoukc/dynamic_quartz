package club.javaprod.quartz.dynamic_quartz.service;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class QuartzService {

    @Autowired
    private Scheduler scheduler;


    /**
     * 修改触发器
     *
     * @param triggerName
     * @param group
     * @param cron
     * @return
     * @throws SchedulerException
     */
    public boolean modifyQuartz(String triggerName, String group, String cron) throws SchedulerException {

        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, group);

        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        String oldCron = trigger.getCronExpression();
        if (!oldCron.equalsIgnoreCase(cron)) {
            CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule(cron);
            CronTrigger cronTrigger = TriggerBuilder.newTrigger().withSchedule(cronSchedule).build();
            Date date = scheduler.rescheduleJob(triggerKey, cronTrigger);
            return date != null;
        }
        return true;
    }


    /**
     * 暂停所有任务
     *
     * @throws SchedulerException
     */
    public void pauseAllJob() throws SchedulerException {
        scheduler.pauseAll();
    }

    /**
     * 暂停某个任务
     *
     * @param name
     * @param group
     * @throws SchedulerException
     */
    public void pauseJob(String name, String group) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(name, group);
        boolean exists = scheduler.checkExists(jobKey);
        if (exists) {
            scheduler.pauseJob(jobKey);
        }
    }


    /**
     * 恢复所有任务
     *
     * @throws SchedulerException
     */
    public void resumeAllJob() throws SchedulerException {
        scheduler.resumeAll();
    }

    /**
     * 恢复某个任务
     *
     * @param name
     * @param group
     * @throws SchedulerException
     */
    public void resumeJob(String name, String group) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(name, group);
        boolean exists = scheduler.checkExists(jobKey);
        if (exists) {
            scheduler.resumeJob(jobKey);
        }

    }

    /**
     * 删除某个任务
     *
     * @param name
     * @param group
     * @throws SchedulerException
     */
    public void deleteJob(String name, String group) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(name, group);
        boolean exists = scheduler.checkExists(jobKey);
        if (exists) {
            scheduler.deleteJob(jobKey);
        }
    }

    /**
     * 获取所有任务列表
     *
     * @return
     * @throws SchedulerException
     */

    public List<JobInfo> getJobList() throws SchedulerException {
        List<JobInfo> jobInfoList = new ArrayList<>();
        Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.anyGroup());
        jobKeys.forEach(p -> {
            JobInfo jobInfo = new JobInfo();
            jobInfo.setName(p.getName());
            jobInfo.setGroup(p.getGroup());
            TriggerKey triggerKey = TriggerKey.triggerKey(p.getName(), p.getGroup());
            try {
                Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);
                jobInfo.setStates(triggerState.toString());
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
            jobInfoList.add(jobInfo);

        });
        return jobInfoList;
    }


    public static class JobInfo {
        private String name;
        private String group;
        private String states;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public String getStates() {
            return states;
        }

        public void setStates(String states) {
            this.states = states;
        }
    }

}
