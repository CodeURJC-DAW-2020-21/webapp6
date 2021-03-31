package es.sixshop.model;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idProduct = null;
	
	@ManyToOne
	private User user;
	
	private String productName;
	private String description;
	private String category;
	private int price;
	private int rating;
	
	@Lob
	@JsonIgnore
	private Blob imageFile;
	private boolean image;
	
	
	//Constructor necesario para la carga desde BBDD
	protected Product() {}
	
	public Product(String productName, String description, String category, int price, User user) {
		super();
		this.productName = productName;
		this.description = description;
		this.category = category;
		this.price = price;
		this.user = user;
		this.rating = 0;
	}
	
	public Product(String productName, String description, String category, int price) {
		super();
		this.productName = productName;
		this.description = description;
		this.category = category;
		this.price = price;
		this.rating = 0;
	}

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Blob getImageFile() {
		return imageFile;
	}

	public void setImageFile(Blob imageFile) {
		this.imageFile = imageFile;
	}

	public boolean isImage() {
		return image;
	}

	public void setImage(boolean image) {
		this.image = image;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}
