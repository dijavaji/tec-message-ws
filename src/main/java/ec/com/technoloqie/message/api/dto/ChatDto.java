package ec.com.technoloqie.message.api.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Jacksonized @Builder
//@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatDto {

	private String userId;	//< Identificador unico del chat al que enviar el mensaje

	//private String senderId;

	private String text;

	private String response;

	private String createdBy;

	private Date createdDate;

	private String modifiedBy;

	private Date modifiedDate;

	private Boolean status;

	private String generationId;
	
	private String assistantName;
	
	@JsonCreator
	public ChatDto(@JsonProperty("id") String id, 
			@JsonProperty("text") String text, 
			@JsonProperty("response") String response, 
			@JsonProperty("createdBy") String createdBy, 
			@JsonProperty("createdDate") Date createdDate,
			@JsonProperty("modifiedBy") String modifiedBy, 
			@JsonProperty("modifiedDate") Date modifiedDate, 
			@JsonProperty("status") Boolean status, 
			@JsonProperty("generationId")String generationId, 
			@JsonProperty("assistantName")String assistantName) {
		this.userId = id;
		//this.senderId = senderId;
		this.text = text;
		this.response = response;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.status = status;
		this.generationId = generationId;
		this.assistantName = assistantName;
	}
	
	
	
	
	//private MessageType type;
	
	/*public enum MessageType{
		CHAT, LEAVE, JOIN
	}*/

}
/*
public class ChatDto {

private Integer id;

//@Column(name="ACCNUMBER",nullable=false, unique=true)
//private Integer accNumber; 

//@Column(name="BALANCE",nullable=false)
//private Double balance;

private String senderId;

private String displayName;

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

private MessageType type;

public enum MessageType{
	CHAT, LEAVE, JOIN
}

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

public MessageType getType() {
	return type;
}

public void setType(MessageType type) {
	this.type = type;
}

public String getDisplayName() {
	return displayName;
}

public void setDisplayName(String displayName) {
	this.displayName = displayName;
}
}*/