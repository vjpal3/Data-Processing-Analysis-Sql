package com.vpal.data.processanalysissql.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vpal.data.processanalysissql.dao.ConsumerComplaintDao;

@RestController
@RequestMapping("/query")
public class QueriesController {
	Logger logger = LoggerFactory.getLogger(QueriesController.class);
	
	private ConsumerComplaintDao consumerComplaintDao;
	
	public QueriesController(ConsumerComplaintDao consumerComplaintDao) {
		this.consumerComplaintDao = consumerComplaintDao;
	}
	
	@GetMapping("/complaints/{state}")
	public ObjectNode statewiseComplaints(@PathVariable String state) {
		long start = System.currentTimeMillis();
		
		ObjectNode node = consumerComplaintDao.statewiseComplaints(state);
		
		long time = System.currentTimeMillis() - start;
		
		logger.info("Execution time -> {}", time);
		return node;
	}
	
	@GetMapping("/complaints/processedsameday")
	public ObjectNode processedSameDay() {
		
		ObjectNode node = consumerComplaintDao.processedSameDay();
		return node;
	}
}
