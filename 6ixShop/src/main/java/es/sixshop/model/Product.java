package es.sixshop.model;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Product {
	public interface Basic{}
	public interface Users{}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Basic.class)
	private Long idProduct = null;
	
	@ManyToOne
	@JsonView(Users.class)
	private User user;
	
	@JsonView(Basic.class)
	private String productName;
	@JsonView(Basic.class)
	private String description;
	@JsonView(Basic.class)
	private String category;
	@JsonView(Basic.class)
	private int price;
	@JsonView(Basic.class)
	private int rating;
	
	@Lob
	@JsonIgnore
	@JsonView(Basic.class)
	private Blob imageFile;
	@JsonView(Basic.class)
	private boolean image;
	
	
	// Constructor necessary for loading from DB
	protected Product() {}
	
	public Product(String productName, String description, String category, int price, User user) {
		super();
		this.productName = productName;
		this.description = description;
		this.category = category;
		this.price = price;
		this.user = user;
		this.rating = -1;
	}
	
	public Product(String productName, String description, String category, int price) {
		super();
		this.productName = productName;
		this.description = description;
		this.category = category;
		this.price = price;
		this.rating = -1;
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
