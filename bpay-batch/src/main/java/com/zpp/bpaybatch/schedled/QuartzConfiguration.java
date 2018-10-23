package com.zpp.bpaybatch.schedled;

import java.util.HashMap;
import java.util.Map;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfiguration {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private JobLocator jobLocator;

	@Bean
	public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
		JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
		jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
		return jobRegistryBeanPostProcessor;
	}

	@Bean
	public JobDetailFactoryBean jobDetailFactoryBean() {
		JobDetailFactoryBean jobFactory = new JobDetailFactoryBean();
		jobFactory.setJobClass(TestTask2.class);
		jobFactory.setGroup("my_group");
		jobFactory.setName("my_job");
		Map<String, Object> map = new HashMap<>();
		map.put("jobName", "importUserJob");
		map.put("jobLauncher", jobLauncher);
		map.put("jobLocator", jobLocator);
		jobFactory.setJobDataAsMap(map);
		return jobFactory;
	}

	@Bean
	public CronTriggerFactoryBean cronTriggerFactoryBean() {
		CronTriggerFactoryBean cTrigger = new CronTriggerFactoryBean();
		System.out.println("------- : " + jobDetailFactoryBean().getObject());
		cTrigger.setJobDetail(jobDetailFactoryBean().getObject());
		cTrigger.setStartDelay(5000);
		cTrigger.setName("my_trigger");
		cTrigger.setGroup("trigger_group");
		cTrigger.setCronExpression("0/3 * * * * ? "); // 每间隔5s触发一次Job任务
		return cTrigger;
	}

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean schedulerFactor = new SchedulerFactoryBean();
		schedulerFactor.setTriggers(cronTriggerFactoryBean().getObject());
		return schedulerFactor;
	}

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
