package com.zpp.bpaybatch.schedled;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

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
public class BatchConfiguration {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private JobLocator jobLocator;

	@Autowired
	private DataSource dataSource;

	@Bean
	public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
		JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
		jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
		return jobRegistryBeanPostProcessor;
	}

	@Bean
	public JobDetailFactoryBean jobDetailFactoryBean() {
		JobDetailFactoryBean jobFactory = new JobDetailFactoryBean();
		jobFactory.setJobClass(TestTask.class);
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
		cTrigger.setJobDetail(jobDetailFactoryBean().getObject());
		cTrigger.setStartDelay(5000);
		cTrigger.setName("my_trigger");
		cTrigger.setGroup("trigger_group");
		cTrigger.setCronExpression("0/30 * * * * ? ");
		return cTrigger;
	}

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean schedulerFactor = new SchedulerFactoryBean();
		schedulerFactor.setTriggers(cronTriggerFactoryBean().getObject());
		schedulerFactor.setDataSource(dataSource);
		return schedulerFactor;
	}
}
