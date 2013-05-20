package com.test.quartz;

import com.test.servlet.*;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;

/**
 * Author: Hu jing ling
 * Date: 12-12-15
 * Description: put description for the type here...
 */
public class TestSimpleJob {

    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler scheduler = sf.getScheduler();

        JobDetail job = JobBuilder.newJob(com.test.servlet.SimpleJob.class)
                .withIdentity("job1", "group1")
                .build();

        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
//                .withSchedule(cronSchedule("0 23 17 ? * *")) //每天凌晨1点
                .withSchedule(cronSchedule("0 */1 * * * ?")) //Every 1 minute
                .build();

        Date date = scheduler.scheduleJob(job, trigger);
        System.out.println("计划任务将运行于:" + date.toLocaleString());
        System.out.println("计划重复于:" + trigger.getCronExpression());


        scheduler.start();
    }

}
