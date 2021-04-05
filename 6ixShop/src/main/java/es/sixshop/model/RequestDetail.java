package es.sixshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class RequestDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idRequestDetail = null;
	
	@ManyToOne
	private Request request;
	
	@OneToOne
	private Product product;
	
	private int productPrice;
	private int rating;
	
	// Constructor necessary for loading from DB
	protected RequestDetail() {}
	
	public RequestDetail(Request request, Product product, int productPrice){
		super();
		this.request = request;
		this.product = product;
		this.productPrice = productPrice;
		this.rating = -1;
	}

	public Long getIdRequestDetail() {
		return idRequestDetail;
	}

	public void setIdRequestDetail(Long idRequestDetail) {
		this.idRequestDetail = idRequestDetail;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}
