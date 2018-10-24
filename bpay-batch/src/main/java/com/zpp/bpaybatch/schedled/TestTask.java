package com.zpp.bpaybatch.schedled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.zpp.bpaybatch.util.SpringContextUtil;

public class TestTask extends QuartzJobBean {
	Logger logger = LoggerFactory.getLogger(getClass());

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
			// ##按天执行 begin
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dataStr = sdf.format(new Date());
			JobParameter currentTime = new JobParameter(dataStr);
			map.put("currentTime", currentTime);
			// ##按天执行 end
			JobExecution jobExecution = jobLauncher.run(job, new JobParameters(map));
			jobExecution.getExitStatus();
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			logger.info("job run failed for {}", e.getMessage());
		} catch (NoSuchJobException e) {
			logger.info("job {} is not exist!", jobName);
		}

	}

}
