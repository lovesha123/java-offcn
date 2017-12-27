package com.offcn.trigger;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.offcn.Heellow;

public class Test {
	
	public static void main(String[] args) throws SchedulerException {
		
	

	JobDetail jobdetail=JobBuilder.newJob(Heellow.class).withIdentity("job1","group1").build();
	
	CronTrigger trigger=TriggerBuilder.newTrigger().withIdentity("trigger1","group2").withSchedule(CronScheduleBuilder.cronSchedule("0/30 55-59 14 * * ?")).build();
	
	Scheduler sched=StdSchedulerFactory.getDefaultScheduler();
	
	Date d=sched.scheduleJob(jobdetail,trigger);
	
	System.out.println("启动调度器"+d.toLocaleString());
	
	sched.start();
	
	try {
		Thread.sleep(120L*1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	System.out.println("关闭调度器");
	
	sched.shutdown();
	
	}
}
