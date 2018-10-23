package com.zpp.bpaybatch.schedled;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfiguration {

	@Bean
	public JobDetail testQuartz1() {
		return JobBuilder.newJob(TestTask1.class).withIdentity("testTask1").storeDurably().build();
	}

	@Bean
	public Trigger testQuartzTrigger1() {
		// 每隔5秒执行一次
		// CronScheduleBuilder.cronSchedule("*/5 * * * * ?");
		// 5秒执行一次
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5)
				.repeatForever();
		return TriggerBuilder.newTrigger().forJob(testQuartz1()).withIdentity("testTask1").withSchedule(scheduleBuilder)
				.build();
	}
}
