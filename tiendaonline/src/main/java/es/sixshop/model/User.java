package es.sixshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idUser;
	
	private boolean checked;
	private String firstName;
	private String password;
	private String mail;
	private String type;
	private String phonenumber;
	private int NTarjeta;
	private String image;

	// Constructor necesario para la carga desde BBDD
	protected User() {}
	
	public User(String firstName, String password, String mail, String phonenumber) {
		this.firstName = firstName;
		this.password = password;
		this.mail = mail;
		this.phonenumber = phonenumber;
	}

}
