package es.sixshop.model;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Order{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idOrder;
	
	private Long idSell;
	private Date date;
	private String state;
	
	//Constructor necesario para la carga desde BBDD
	protected Order() {}

	public Order(Long idSell, Date date, String state) {
		super();
		this.idSell = idSell;
		this.date = date;
		this.state = state;
	}

	public Long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}

	public Long getIdSell() {
		return idSell;
	}

	public void setIdSell(Long idSell) {
		this.idSell = idSell;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	
	
}
