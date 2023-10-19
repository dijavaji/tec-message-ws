package ec.com.technoloqie.message.api.model;

import java.util.Date;

public class ChatDto {
	
	private Integer id;
	
	//@Column(name="ACCNUMBER",nullable=false, unique=true)
    //private Integer accNumber; 
	
	//@Column(name="BALANCE",nullable=false)
	//private Double balance;
	
	private String senderId;
	
	private String text;
	
	private String response;
	
	private String data;
	
	//@NotEmpty(message ="no puede estar vacio")
	//@Column(name="CREATEDBY",nullable=false)
	private String createdBy;
	
	//@Column(name="CREATEDDATE",nullable=false)
	//@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	//@Column(name="MODIFIEDBY")
	private String modifiedBy;
	
	//@Column(name="MODIFIEDDATE")
	//@Temporal(TemporalType.DATE)
	private Date modifiedDate;
	
	//@Column(name="STATUS")
	private Boolean status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
