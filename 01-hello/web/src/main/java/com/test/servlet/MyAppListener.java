package com.test.servlet; /**
 * User: 胡荆陵
 * Date: 12-12-14
 * 项目启动时，启动 quartz 计划任务来处理 JMS 消息队列
 */

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;

@WebListener()
public class MyAppListener implements ServletContextListener {

    // Public constructor is required by servlet spec
    public MyAppListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String cron = context.getInitParameter("cron");
        try {
            SchedulerFactory sf = new StdSchedulerFactory();
            Scheduler scheduler = sf.getScheduler();

            JobDetail job = JobBuilder.newJob(SimpleJob.class)
            .build();

            CronTrigger trigger = TriggerBuilder.newTrigger()
            .withSchedule(cronSchedule(cron))
            .build();

            Date date = scheduler.scheduleJob(job, trigger);
            System.out.println("计划任务将运行于:"+date.toLocaleString());

            scheduler.start();//启动调度
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
}
