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
	private String nickname;
	private String password;
	private String mail;
	private String type;
	private int phonenumber;
	private int NTarjeta;
	private String image;

	//Constructor necesario para la carga desde BBDD
	protected User() {}
	
	public User(String nickname, String password, String mail, int phonenumber) {
		super();
		this.nickname = nickname;
		this.password = password;
		this.mail = mail;
		this.phonenumber = phonenumber;
	}

}
