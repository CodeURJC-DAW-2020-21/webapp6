package es.sixshop.model;

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
	
	private String name;
	private String description;
	private String category;
	private int price;
	//private String image;
	
	//private ImageService image;
	
	
	//Constructor necesario para la carga desde BBDD
	protected Product() {}
	
	public Product(String name, String description, String category, int price) {
		super();
		this.name = name;
		this.description = description;
		this.category = category;
		this.price = price;
		
	}
	
	public Long getIdProduct() {
		return idProduct;
	}

}
