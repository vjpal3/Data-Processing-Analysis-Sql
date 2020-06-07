package com.vpal.data.processanalysissql.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

import com.vpal.data.processanalysissql.dao.ConsumerComplaintDao;

public class Listener extends JobExecutionListenerSupport {
	  private static final Logger log = LoggerFactory.getLogger(Listener.class);
	  
	  private final ConsumerComplaintDao consumerComplaintDao;
	 
	  public Listener(ConsumerComplaintDao consumerComplaintDao) {
	    this.consumerComplaintDao = consumerComplaintDao;
	  }
	 
	  @Override
	  public void afterJob(JobExecution jobExecution) {
	    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
	      log.info("Finish Job! You can run the queries.");
	    }
	  }
	}
