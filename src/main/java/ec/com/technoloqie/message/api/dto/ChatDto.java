package ec.com.technoloqie.message.api.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
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
public class ChatDto {

	private Integer id;

	// @Column(name="ACCNUMBER",nullable=false, unique=true)
	// private Integer accNumber;

	// @Column(name="BALANCE",nullable=false)
	// private Double balance;

	private String senderId;

	private String text;

	private String response;

	// @NotEmpty(message ="no puede estar vacio")
	// @Column(name="CREATEDBY",nullable=false)
	private String createdBy;

	// @Column(name="CREATEDDATE",nullable=false)
	// @Temporal(TemporalType.DATE)
	private Date createdDate;

	// @Column(name="MODIFIEDBY")
	private String modifiedBy;

	// @Column(name="MODIFIEDDATE")
	// @Temporal(TemporalType.DATE)
	private Date modifiedDate;

	// @Column(name="STATUS")
	private Boolean status;
	//private String displayName;	
	private String generationId;
	//private String data;
	@JsonCreator
	public ChatDto(@JsonProperty("id") Integer id, 
			@JsonProperty("senderId") String senderId, 
			@JsonProperty("text") String text, 
			@JsonProperty("response") String response, 
			@JsonProperty("createdBy") String createdBy, 
			@JsonProperty("createdDate") Date createdDate,
			@JsonProperty("modifiedBy") String modifiedBy, 
			@JsonProperty("modifiedDate") Date modifiedDate, 
			@JsonProperty("status") Boolean status, 
			@JsonProperty("generationId")String generationId) {
		this.id = id;
		this.senderId = senderId;
		this.text = text;
		this.response = response;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.status = status;
		this.generationId = generationId;
	}
	
	
	
	
	//private MessageType type;
	
	/*public enum MessageType{
		CHAT, LEAVE, JOIN
	}*/

}