package com.vpal.data.processanalysissql.batch;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.vpal.data.processanalysissql.model.ConsumerComplaint;

public class Processor implements ItemProcessor<ConsumerComplaint, ConsumerComplaint> {
	 
	  private static final Logger log = LoggerFactory.getLogger(Processor.class);
	 
	  @Override
	  public ConsumerComplaint process(ConsumerComplaint complaint) throws Exception {
	    return complaint;
	  }
	}
