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

import com.vpal.data.processanalysissql.batch.Listener;
import com.vpal.data.processanalysissql.batch.Processor;
import com.vpal.data.processanalysissql.batch.Reader;
import com.vpal.data.processanalysissql.batch.Writer;
import com.vpal.data.processanalysissql.dao.ConsumerComplaintDao;
import com.vpal.data.processanalysissql.model.ConsumerComplaint;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	@Autowired
	  public JobBuilderFactory jobBuilderFactory;
	 
	  @Autowired
	  public StepBuilderFactory stepBuilderFactory;
	 
	  @Autowired
	  public ConsumerComplaintDao consumerComplaintDao;
	 
	  @Bean
	  public Job job() {
	    return jobBuilderFactory.get("job").incrementer(new RunIdIncrementer()).listener(new Listener(consumerComplaintDao))
	        .flow(step1()).end().build();
	  }
	 
	  @Bean
	  public Step step1() {
	    return stepBuilderFactory.get("step1").<ConsumerComplaint, ConsumerComplaint>chunk(2)
	        .reader(Reader.reader("ConsumerComplaints.csv"))
	        .processor(new Processor()).writer(new Writer(consumerComplaintDao)).build();
	  }
}
