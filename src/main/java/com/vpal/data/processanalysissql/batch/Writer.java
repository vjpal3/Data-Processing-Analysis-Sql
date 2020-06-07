package com.vpal.data.processanalysissql.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.vpal.data.processanalysissql.dao.ConsumerComplaintDao;
import com.vpal.data.processanalysissql.model.ConsumerComplaint;

public class Writer implements ItemWriter<ConsumerComplaint> {
	 
	  private final ConsumerComplaintDao consumerComplaintDao;
	  
	  public Writer(ConsumerComplaintDao complaintDao) {
	    this.consumerComplaintDao = complaintDao;
	  }
	 
	  @Override
	  public void write(List<? extends ConsumerComplaint> complaints) throws Exception {
		  consumerComplaintDao.insert(complaints);
	  }
	}
