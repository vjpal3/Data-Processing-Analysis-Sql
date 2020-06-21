package com.vpal.data.processanalysissql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
	
	class ConsumerComplaintRowMapper implements RowMapper<ConsumerComplaint> {

		@Override
		public ConsumerComplaint mapRow(ResultSet rs, int rowNum) throws SQLException {
			ConsumerComplaint complaint = new ConsumerComplaint();
			complaint.setId(rs.getLong("id"));
			complaint.setDateReceived(rs.getDate("date_received"));
	        complaint.setProductName(rs.getString("product_name"));
	        complaint.setIssue(rs.getString("issue"));
	        complaint.setConsumerComplaintNarrative(rs.getString("consumer_complaint_narrative"));
	        complaint.setCompanyPublicResponse(rs.getString("company_public_response"));
	        complaint.setCompany(rs.getString("company"));
	        complaint.setStateName(rs.getString("state_name"));
	        complaint.setZipCode(rs.getString("zip_code"));
	        complaint.setSubmittedVia(rs.getString("submitted_via"));
	        complaint.setDateSent(rs.getDate("date_sent"));
	        complaint.setCompanyResponseToConsumer(rs.getString("company_response_to_consumer"));
	        complaint.setTimelyResponse(rs.getString("timely_response"));
	        complaint.setConsumerDisputed(rs.getString("consumer_disputed"));
	        complaint.setComplaintId(rs.getInt("complaint_id"));
	        
			return complaint;
		}
	}
		
	@Override
	@Transactional
	public void insert(List<? extends ConsumerComplaint> ConsumerComplaints) {
//		String sql = "INSERT INTO consumer_complaint " + "(date_received, product_name, sub_product, "
//				+ "issue, sub_issue, consumer_complaint_narrative, company_public_response, "
//				+ "company, state_name, zip_code, tags, consumer_consent_provided, "
//				+ "submitted_via,  date_sent, company_response_to_consumer, timely_response, "
//				+ "consumer_disputed, complaint_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO consumer_complaint ");
		sql.append("(date_received, product_name, sub_product, ");
		sql.append("issue, sub_issue, consumer_complaint_narrative, company_public_response, ");
		sql.append("company, state_name, zip_code, tags, consumer_consent_provided, ");
		sql.append("submitted_via,  date_sent, company_response_to_consumer, timely_response, ");
		sql.append("consumer_disputed, complaint_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		getJdbcTemplate().batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
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

	@Override
	public ObjectNode statewiseComplaints(String state) {
		state = state.toUpperCase();
		
		String sql = "SELECT * FROM consumer_complaint where state_name = ?";
		List<ConsumerComplaint> complaints = getJdbcTemplate().query(sql, new Object[] { state }, new BeanPropertyRowMapper<ConsumerComplaint>(ConsumerComplaint.class));
				
		final JsonNodeFactory factory = JsonNodeFactory.instance;
		
		//create parent node
		final ObjectNode resultNode = factory.objectNode();
		
		String query = "Complaints Received in the state of " + state;
		resultNode.put("query", query);
		resultNode.put("Count", complaints.size());
		
		// Create child node
		final ObjectNode complaintNode = factory.objectNode();  
		int index = 0;
		for (ConsumerComplaint complaint : complaints)
			complaintNode.putPOJO(Integer.toString(++index), complaint);
		
		resultNode.set("List of Complaints", complaintNode);
		return resultNode;
	}
	
	@Override
	public ObjectNode getIssue(String issue) {
		String searchPhrase = "%" + issue + "%";
		String sql = String.join(
				System.getProperty("line.separator"), 
				"SELECT id, date_received, product_name, issue, consumer_complaint_narrative, company_public_response, ",
				"company, state_name, zip_code, submitted_via, date_sent, company_response_to_consumer, ", 
				"timely_response, consumer_disputed, complaint_id ",
				" FROM consumer_complaint WHERE issue ILIKE ?"
		);

		List<ConsumerComplaint> complaints = getJdbcTemplate().query(sql, new Object[] { searchPhrase }, new ConsumerComplaintRowMapper());
		
		final JsonNodeFactory factory = JsonNodeFactory.instance;
		//create parent node
		final ObjectNode resultNode = factory.objectNode();
		
		String query = "Complaints with word '" + issue + "' in issue field";
		resultNode.put("query", query);
		resultNode.put("Count", complaints.size());
		
		// Create child node
		final ObjectNode complaintNode = factory.objectNode();  
		int index = 0;
		for (ConsumerComplaint complaint : complaints)
			complaintNode.putPOJO(Integer.toString(++index), complaint);
		
		resultNode.set("List of Complaints", complaintNode);
		
		return resultNode;
	}

	@Override
	public ObjectNode processedSameDay() {
		
		String sql = "select count(id) from consumer_complaint where date_received = date_sent";
		int totalCompalints = getJdbcTemplate().queryForObject(sql, Integer.class);
		
		final JsonNodeFactory factory = JsonNodeFactory.instance;
		
		//create parent node
		final ObjectNode resultNode = factory.objectNode();
		
		String query = "Complaints received and sent on the same date";
		resultNode.put("query", query);
		resultNode.put("Count", totalCompalints);
		return resultNode;
	}

	@Override
	public ObjectNode getProducts(String product) {
		
		String searchPhrase = "%" + product + "%";
		String sql = String.join(
				System.getProperty("line.separator"), 
				"SELECT id, date_received, product_name, issue, consumer_complaint_narrative, company_public_response, ",
				"company, state_name, zip_code, submitted_via, date_sent, company_response_to_consumer, ", 
				"timely_response, consumer_disputed, complaint_id ",
				" FROM consumer_complaint WHERE product_name ILIKE ?"
		);

		List<ConsumerComplaint> complaints = getJdbcTemplate().query(sql, new Object[] { searchPhrase }, new ConsumerComplaintRowMapper());
		
		final JsonNodeFactory factory = JsonNodeFactory.instance;
		//create parent node
		final ObjectNode resultNode = factory.objectNode();
		
		String query = "Complaints with word '" + product + "' in product field";
		resultNode.put("query", query);
		resultNode.put("Count", complaints.size());
		
		// Create child node
		final ObjectNode complaintNode = factory.objectNode();  
		int index = 0;
		for (ConsumerComplaint complaint : complaints)
			complaintNode.putPOJO(Integer.toString(++index), complaint);
		
		resultNode.set("List of Complaints", complaintNode);
		
		return resultNode;
	}
}

