package com.vpal.data.processanalysissql.dao;

import java.util.List;

import com.vpal.data.processanalysissql.model.ConsumerComplaint;

public interface ConsumerComplaintDao {
	
	public void insert(List<? extends ConsumerComplaint> ConsumerComplaints);

}
