package es.sixshop.model;

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
import javax.persistence.OneToOne;

@Entity
public class Request{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idRequest = null;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	private List<RequestDetail> lRequestDetail;
	
	@ManyToOne
	private User buyerUser;
	
	private LocalDate date;
	private String status;
	
	//Constructor necesario para la carga desde BBDD
	protected Request() {}

	public Request(User buyerUser) {
		super();
		this.buyerUser = buyerUser;
		this.status = "Cart";
		this.date = LocalDate.now();
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	////////////////////////////////////////////
	public void setRequestDetail(RequestDetail requestDetail) {
		lRequestDetail.add(requestDetail);
	}
}
