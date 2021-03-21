package es.sixshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import es.sixshop.service.ImageService;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idProduct;
	
	private Long idUser;
	private String productName;
	private String description;
	private String category;
	private int price;
	private int rating;
	
	//Constructor necesario para la carga desde BBDD
	protected Product() {}
	
	public Product(String productName, String description, String category, int price, long idUser, int rating) {
		super();
		this.productName = productName;
		this.description = description;
		this.category = category;
		this.price = price;
		this.idUser = idUser;
		this.rating = rating;
	}
	
	public Product(String productName, String description, String category, int price, int rating) {
		super();
		this.productName = productName;
		this.description = description;
		this.category = category;
		this.price = price;
		this.idUser = (long) 99999999;
		this.rating = rating;
	}
	
	public Long getIdProduct() {
		return idProduct;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
}
