package com.vpal.data.processanalysissql.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.vpal.data.processanalysissql.batch.Listener;
import com.vpal.data.processanalysissql.batch.Processor;
import com.vpal.data.processanalysissql.batch.Reader;
import com.vpal.data.processanalysissql.batch.Writer;
import com.vpal.data.processanalysissql.dao.ConsumerComplaintDao;
import com.vpal.data.processanalysissql.model.ConsumerComplaint;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	private static final int THREAD_NUMBER = 10;
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
 
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
 
	@Autowired
	public ConsumerComplaintDao consumerComplaintDao;
 
	@Bean
	public Job job() {
		return jobBuilderFactory.get("job")
				.incrementer(new RunIdIncrementer())
				.listener(new Listener(consumerComplaintDao))
				.flow(step1())
				.end()
				.build();
	}
 
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<ConsumerComplaint, ConsumerComplaint>chunk(1000)
				.reader(Reader.reader("ConsumerComplaints.csv"))
		        .processor(new Processor())
		        .writer(new Writer(consumerComplaintDao))
		        .build();
	}
}
