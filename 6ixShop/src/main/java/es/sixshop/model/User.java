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

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idUser = null;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="user")
	private List<Product> lProducts;
	
	@OneToMany
	private List<Product> lCart;
	
	@OneToMany(mappedBy="buyerUser")
	private List<Request> lRequest;
	
	@Column(unique = true)
	private String nickname;
	private String encodedPassword;
	private String mail;
	private int phonenumber;
	private String image;

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	/////////////////////////////////////////////////////////////////////
	
	public void setRequest(Request request) {
		lRequest.add(request);
	}
}
