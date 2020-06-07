package com.vpal.data.processanalysissql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vpal.data.processanalysissql.dao.ConsumerComplaintDao;
import com.vpal.data.processanalysissql.model.ConsumerComplaint;

@Repository
public class ConsumerComplaintDaoImpl extends JdbcDaoSupport implements ConsumerComplaintDao{
	
	@Autowired
	DataSource dataSource;
	 
	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Override
	@Transactional
	public void insert(List<? extends ConsumerComplaint> ConsumerComplaints) {
		String sql = "INSERT INTO consumer_complaint " + "(date_received, product_name, sub_product, "
				+ "issue, sub_issue, consumer_complaint_narrative, company_public_response, "
				+ "company, state_name, zip_code, tags, consumer_consent_provided, "
				+ "submitted_via,  date_sent, company_response_to_consumer, timely_response, "
				+ "consumer_disputed, complaint_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
		      public void setValues(PreparedStatement ps, int i) throws SQLException {
		    	  
		    	ConsumerComplaint complaint = ConsumerComplaints.get(i);
		        
		        ps.setDate(1, new java.sql.Date(complaint.getDateReceived().getTime()));
		        ps.setString(2, complaint.getProductName());
		        ps.setString(3, complaint.getSubProduct());
		        ps.setString(4, complaint.getIssue());
		        ps.setString(5, complaint.getSubIssue());
		        ps.setString(6, complaint.getConsumerComplaintNarrative());
		        ps.setString(7, complaint.getCompanyPublicResponse());
		        ps.setString(8, complaint.getCompany());
		        ps.setString(9, complaint.getStateName());
		        ps.setString(10, complaint.getZipCode());
		        ps.setString(11, complaint.getTags());
		        ps.setString(12, complaint.getConsumerConsentProvided());
		        ps.setString(13, complaint.getSubmittedVia());
		        ps.setDate(14, new java.sql.Date(complaint.getDateSent().getTime()));
		        ps.setString(15, complaint.getCompanyResponseToConsumer());
		        ps.setString(16, complaint.getTimelyResponse());
		        ps.setString(17, complaint.getConsumerDisputed());
		        ps.setInt(18, complaint.getComplaintId());
		      }
		      
		      public int getBatchSize() {
		          return ConsumerComplaints.size();
		        }
		      });
	}
}
