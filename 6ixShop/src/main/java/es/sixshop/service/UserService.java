package es.sixshop.service;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.sixshop.model.User;
import es.sixshop.repository.UserRepository;
import es.sixshop.security.SecurityConfiguration;

@Service
public class UserService {

	@Autowired
	private UserRepository userR;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostConstruct //Se ejecuta después de haber inyectado las dependencias
	public void init() {
		save(new User("SixShop",passwordEncoder.encode("admin"),"admin@sixshop.es",666666666,"ADMIN"));
		save(new User("CarlosCuesta",passwordEncoder.encode("123"),"carloscuesta@gmail.com",666777000,"USER"));
	}
	
	public Optional<User> findById(long idUser){
		return userR.findById(idUser);
	}
	
	public void save(User user) {
		userR.save(user);
	}
}
