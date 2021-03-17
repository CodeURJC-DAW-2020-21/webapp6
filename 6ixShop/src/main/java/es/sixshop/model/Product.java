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
	private String name;
	private String description;
	private String category;
	private int price;
	
	//Constructor necesario para la carga desde BBDD
	protected Product() {}
	
	public Product(String name, String description, String category, int price, long idUser) {
		super();
		this.name = name;
		this.description = description;
		this.category = category;
		this.price = price;
		this.idUser = idUser;
	}
	
	public Long getIdProduct() {
		return idProduct;
	}

}
