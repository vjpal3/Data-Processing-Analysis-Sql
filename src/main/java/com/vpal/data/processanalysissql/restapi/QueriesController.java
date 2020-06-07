package com.vpal.data.processanalysissql.restapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vpal.data.processanalysissql.dao.ConsumerComplaintDao;

@RestController
@RequestMapping("/query")
public class QueriesController {
	
	private ConsumerComplaintDao consumerComplaintDao;
	
	public QueriesController(ConsumerComplaintDao consumerComplaintDao) {
		this.consumerComplaintDao = consumerComplaintDao;
	}
	
	@GetMapping("/complaints/{state}")
	public ObjectNode statewiseComplaints(@PathVariable String state) {
		
		return consumerComplaintDao.statewiseComplaints(state);
	}
}
