package es.sixshop.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idOrder;
	
	private Long idSale;
	private Date date;
	private String state;
	
	//Constructror necesario para la carga desde BBDD
	protected Order(){}

	public Order(Date date, String state) {
		super();
		this.date = date;
		this.state = state;
	};

	
	
}
