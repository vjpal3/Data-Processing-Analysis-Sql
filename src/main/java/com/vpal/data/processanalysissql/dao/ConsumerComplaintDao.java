package com.vpal.data.processanalysissql.dao;

import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vpal.data.processanalysissql.model.ConsumerComplaint;

public interface ConsumerComplaintDao {
	
	public void insert(List<? extends ConsumerComplaint> ConsumerComplaints);
	
	public  ObjectNode statewiseComplaints(String state);

}
