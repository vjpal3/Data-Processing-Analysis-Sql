package com.vpal.data.processanalysissql.model;

import java.time.Instant;

public class ConsumerComplaint {
	
	private long id;
    private Instant dateReceived;
    private String productName ;
    private String subProduct ;
    private String issue;
    private String subIssue ;
    private String consumerComplaintNarrative;
    private String companyPublicResponse;
    private String company;
    private String stateName;
    private String zipCode ;
    private String tags;
    private String consumerConsentProvided;
    private String submittedVia ;
    private Instant dateSent;
    private String companyResponseToConsumer ;
    private boolean timelyResponse;
    private boolean consumerDisputed;
    private int complaintId;
    
    public ConsumerComplaint() {
	}
    
	public ConsumerComplaint(long id, Instant dateReceived, String productName, String subProduct,
			String issue, String subIssue, String consumerComplaintNarrative, String companyPublicResponse,
			String company, String stateName, String zipCode, String tags, String consumerConsentProvided,
			String submittedVia, Instant dateSent, String companyResponseToConsumer, boolean timelyResponse,
			boolean consumerDisputed, int complaintId) {
		super();
		this.id = id;
		this.complaintId = complaintId;
		this.dateReceived = dateReceived;
		this.productName = productName;
		this.subProduct = subProduct;
		this.issue = issue;
		this.subIssue = subIssue;
		this.consumerComplaintNarrative = consumerComplaintNarrative;
		this.companyPublicResponse = companyPublicResponse;
		this.company = company;
		this.stateName = stateName;
		this.zipCode = zipCode;
		this.tags = tags;
		this.consumerConsentProvided = consumerConsentProvided;
		this.submittedVia = submittedVia;
		this.dateSent = dateSent;
		this.companyResponseToConsumer = companyResponseToConsumer;
		this.timelyResponse = timelyResponse;
		this.consumerDisputed = consumerDisputed;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}

	public Instant getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(Instant dateReceived) {
		this.dateReceived = dateReceived;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSubProduct() {
		return subProduct;
	}

	public void setSubProduct(String subProduct) {
		this.subProduct = subProduct;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getSubIssue() {
		return subIssue;
	}

	public void setSubIssue(String subIssue) {
		this.subIssue = subIssue;
	}

	public String getConsumerComplaintNarrative() {
		return consumerComplaintNarrative;
	}

	public void setConsumerComplaintNarrative(String consumerComplaintNarrative) {
		this.consumerComplaintNarrative = consumerComplaintNarrative;
	}

	public String getCompanyPublicResponse() {
		return companyPublicResponse;
	}

	public void setCompanyPublicResponse(String companyPublicResponse) {
		this.companyPublicResponse = companyPublicResponse;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getConsumerConsentProvided() {
		return consumerConsentProvided;
	}

	public void setConsumerConsentProvided(String consumerConsentProvided) {
		this.consumerConsentProvided = consumerConsentProvided;
	}

	public String getSubmittedVia() {
		return submittedVia;
	}

	public void setSubmittedVia(String submittedVia) {
		this.submittedVia = submittedVia;
	}

	public Instant getDateSent() {
		return dateSent;
	}

	public void setDate_sent(Instant dateSent) {
		this.dateSent = dateSent;
	}

	public String getCompanyResponseToConsumer() {
		return companyResponseToConsumer;
	}

	public void setCompanyResponseToConsumer(String companyResponseToConsumer) {
		this.companyResponseToConsumer = companyResponseToConsumer;
	}

	public boolean getTimelyResponse() {
		return timelyResponse;
	}

	public void setTimelyResponse(boolean timelyResponse) {
		this.timelyResponse = timelyResponse;
	}

	public boolean getConsumerDisputed() {
		return consumerDisputed;
	}

	public void setConsumerDisputed(boolean consumerDisputed) {
		this.consumerDisputed = consumerDisputed;
	}

	@Override
	public String toString() {
		return "ConsumerComplaint [id=" + id + ", complaintId=" + complaintId + ", dateReceived=" + dateReceived
				+ ", productName=" + productName + ", subProduct=" + subProduct + ", issue=" + issue + ", subIssue="
				+ subIssue + ", consumerComplaintNarrative=" + consumerComplaintNarrative + ", companyPublicResponse="
				+ companyPublicResponse + ", company=" + company + ", stateName=" + stateName + ", zipCode=" + zipCode
				+ ", tags=" + tags + ", consumerConsentProvided=" + consumerConsentProvided + ", submittedVia="
				+ submittedVia + ", date_sent=" + dateSent + ", companyResponseToConsumer=" + companyResponseToConsumer
				+ ", timelyResponse=" + timelyResponse + ", consumerDisputed=" + consumerDisputed + "]";
	}
    
    
}




