package com.zpp.bpaybatch.schedled;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class TestTask2 extends QuartzJobBean {
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("TestQuartz02----" + sdf.format(new Date()));
		JobDetail jobDetail = context.getJobDetail();
		JobDataMap jobDataMap = jobDetail.getJobDataMap();
		String jobName = jobDataMap.getString("jobName");
		JobLauncher jobLauncher = (JobLauncher) jobDataMap.get("jobLauncher");
		JobLocator jobLocator = (JobLocator) jobDataMap.get("jobLocator");
		System.out.println("jobName : " + jobName);
		System.out.println("jobLauncher : " + jobLauncher);
		System.out.println("jobLocator : " + jobLocator);

		try {
			Job job = jobLocator.getJob(jobName);
			/* 启动spring batch的job */
			JobExecution jobExecution = jobLauncher.run(job, new JobParameters());
			jobExecution.getExitStatus();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
