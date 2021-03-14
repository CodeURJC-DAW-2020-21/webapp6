package es.sixshop.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idUser;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
	
	@Column(unique = true)
	private String nickname;
	private String encodedPassword;
	private String mail;
	private int phonenumber;
	private int cardNumber;
	private String image;

	//Constructor necesario para la carga desde BBDD
	protected User() {}
	
	public User(String nickname, String encodedPassword, String mail, int phonenumber, String... roles) {
		super();
		this.nickname = nickname;
		this.encodedPassword = encodedPassword;
		this.mail = mail;
		this.phonenumber = phonenumber;
		if (roles==null) {
			this.roles = List.of("USER");
		} else this.roles = List.of(roles);
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public Long getIdUser() {
		return idUser;
	}

	public List<String> getRoles() {
		return roles;
	}

	public String getNickname() {
		return nickname;
	}

	public String getEncodedPassword() {
		return encodedPassword;
	}			
}
