package com.offcn.trigger;

import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.offcn.Heellow;

public class TestSimpleTrigger {

	
	public static void main(String[] args) throws SchedulerException {
		
		// 创建一个JobDetail
		JobDetail jobdetail=JobBuilder.newJob(Heellow.class).withIdentity("job1","group1").build();
		
		//创建Date
		Date startdDate=new Date(System.currentTimeMillis()+10*1000); 
		//创建一个触发器
		SimpleTrigger trigger=TriggerBuilder.newTrigger().withIdentity("trigger1", "group2").withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(10)/*重复的次数*/.withIntervalInSeconds(10)/*重复的间隔*/).startAt(startdDate).build();
		
		//创建调度器
		Scheduler sched=StdSchedulerFactory.getDefaultScheduler();
		
		Date ft=sched.scheduleJob(jobdetail, trigger);
		
		System.out.println("启动调度器"+ft.toLocaleString());
		
		//启动调度器
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
