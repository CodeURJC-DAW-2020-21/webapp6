package es.sixshop.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class User {
	public interface Basic{}
	public interface Products{}
	public interface Owner{}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Owner.class)
	private Long idUser = null;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@JsonView(Basic.class)
	private List<String> roles;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="user")
	@JsonView(Products.class)
	private List<Product> lProducts;
	
	@OneToMany
	@JsonIgnore 
	private List<Product> lCart;
	
	@OneToMany(mappedBy="buyerUser")
	@JsonIgnore 
	private List<Request> lRequest;
	
	@Column(unique = true)
	@JsonView(Basic.class)
	private String nickname;
	@JsonView(Owner.class)
	private String encodedPassword;
	@JsonView(Basic.class)
	private String mail;
	@JsonView(Owner.class)
	private int phonenumber;

	// Constructor necessary for loading from DB
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
		
		this.lProducts = new ArrayList<Product>();
		this.lCart = new ArrayList<Product>();
		this.lRequest = new ArrayList<Request>();
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<Product> getlProducts() {
		return lProducts;
	}

	public void setlProducts(List<Product> lProducts) {
		this.lProducts = lProducts;
	}

	public List<Product> getlCart() {
		return lCart;
	}

	public void setlCart(List<Product> lCart) {
		this.lCart = lCart;
	}

	public List<Request> getlRequest() {
		return lRequest;
	}

	public void setlRequest(List<Request> lRequest) {
		this.lRequest = lRequest;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEncodedPassword() {
		return encodedPassword;
	}

	public void setEncodedPassword(String encodedPassword) {
		this.encodedPassword = encodedPassword;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(int phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	/////////////////////////////////////////////////////////////////////
	
	public void setRequest(Request request) {
		lRequest.add(request);
	}
}
