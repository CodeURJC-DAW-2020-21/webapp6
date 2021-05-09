package es.sixshop.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Request{
	public interface Basic{}
	public interface RequestDetails{}
	public interface Users{}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Basic.class)
	private Long idRequest = null;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	@JsonView(RequestDetails.class)
	private List<RequestDetail> lRequestDetail;
	
	@ManyToOne
	@JsonView(Users.class)
	private User buyerUser;
	
	@JsonView(Basic.class)
	private Date date;
	@JsonView(Basic.class)
	private String status;
	
	@JsonIgnore
	@JsonView(Basic.class)
	private int totalPrice;
	
	// Constructor necessary for loading from DB
	protected Request() {}

	public Request(User buyerUser) {
		super();
		this.buyerUser = buyerUser;
		this.status = "Cart";
		this.date = Date.valueOf(LocalDate.now());
		this.lRequestDetail = new ArrayList<RequestDetail>();
	}
	
	public Request(User buyerUser, String status) { //PAID
		super();
		this.buyerUser = buyerUser;
		this.status = status;
		this.date = Date.valueOf(LocalDate.now());
		this.lRequestDetail = new ArrayList<RequestDetail>();
	}

	public Long getIdRequest() {
		return idRequest;
	}

	public void setIdRequest(Long idRequest) {
		this.idRequest = idRequest;
	}

	public List<RequestDetail> getlRequestDetail() {
		return lRequestDetail;
	}

	public void setlRequestDetail(List<RequestDetail> lRequestDetail) {
		this.lRequestDetail = lRequestDetail;
	}

	public User getBuyerUser() {
		return buyerUser;
	}

	public void setBuyerUser(User buyerUser) {
		this.buyerUser = buyerUser;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	////////////////////////////////////////////
	public void setRequestDetail(RequestDetail requestDetail) {
		lRequestDetail.add(requestDetail);
	}
}
