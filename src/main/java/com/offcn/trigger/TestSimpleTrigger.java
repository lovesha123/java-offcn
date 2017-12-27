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
		
		// ����һ��JobDetail
		JobDetail jobdetail=JobBuilder.newJob(Heellow.class).withIdentity("job1","group1").build();
		
		//����Date
		Date startdDate=new Date(System.currentTimeMillis()+10*1000); 
		//����һ��������
		SimpleTrigger trigger=TriggerBuilder.newTrigger().withIdentity("trigger1", "group2").withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(10)/*�ظ��Ĵ���*/.withIntervalInSeconds(10)/*�ظ��ļ��*/).startAt(startdDate).build();
		
		//����������
		Scheduler sched=StdSchedulerFactory.getDefaultScheduler();
		
		Date ft=sched.scheduleJob(jobdetail, trigger);
		
		System.out.println("����������"+ft.toLocaleString());
		
		//����������
		sched.start();
		
		try {
			Thread.sleep(120L*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("�رյ�����");
		
		sched.shutdown();
	}
}
