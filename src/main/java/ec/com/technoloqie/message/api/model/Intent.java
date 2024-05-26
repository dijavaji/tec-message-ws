package ec.com.technoloqie.message.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


//@Entity
//@Table(name="SCBTFAQSTAGED")
public class Intent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="FAQSTAGEDID",nullable=false, unique=true)
	private Integer id;
	
	@Column(name="INTENTNAME",nullable=false)
    private String name; 
	
	@Column(name="QUERY")
	private String query;
	
	@Column(name="RESPONSE")
	private String response;
	
	//@NotEmpty(message ="no puede estar vacio")
	//@Column(name="CREATEDBY",nullable=false)
	//private String createdBy;
	
	@Column(name="LOADDATE",nullable=false)
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	/*@Column(name="MODIFIEDBY")
	private String modifiedBy;
	
	@Column(name="MODIFIEDDATE")
	@Temporal(TemporalType.DATE)
	private Date modifiedDate;
	
	@Column(name="STATUS")
	private Boolean status;
	
	@ManyToOne
	@JoinColumn(name="ACCTYPID",nullable=false)
	private AccountType accountType;
	
	@ManyToOne
	@JoinColumn(name="CUSTOMERID",nullable=false)
	private Customer customer;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ACCOUNTID")
	private Collection<AccountTransaction> accTranCol;
	
	public Account() {
		this.accTranCol = new ArrayList<>();
	}*/
	
    /*@PrePersist 
	public void prePersist() {
		createdDate = new Date();
		status = Boolean.TRUE;
	}*/

	private static final long serialVersionUID = -979071489122757786L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}
