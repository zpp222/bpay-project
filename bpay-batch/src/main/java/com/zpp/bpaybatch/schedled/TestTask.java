package com.zpp.bpaybatch.schedled;

import java.util.HashMap;
import java.util.Map;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.zpp.bpaybatch.util.SpringContextUtil;

public class TestTask extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		JobDetail jobDetail = context.getJobDetail();
		JobDataMap jobDataMap = jobDetail.getJobDataMap();
		String jobName = jobDataMap.getString("jobName");
		JobLauncher jobLauncher = (JobLauncher) SpringContextUtil.getBean(JobLauncher.class);
		JobLocator jobLocator = (JobLocator) SpringContextUtil.getBean(JobLocator.class);
		try {
			Job job = jobLocator.getJob(jobName);
			// 批量任务执行参数，区分是否为同一个任务（执行一次）
			Map<String, JobParameter> map = new HashMap<>();
			JobParameter currentTimeMillis = new JobParameter(System.currentTimeMillis());
			map.put("currentTimeMillis", currentTimeMillis);
			JobExecution jobExecution = jobLauncher.run(job, new JobParameters(map));
			jobExecution.getExitStatus();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
