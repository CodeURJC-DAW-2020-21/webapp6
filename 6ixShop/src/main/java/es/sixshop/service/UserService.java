package es.sixshop.service;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sixshop.model.User;
import es.sixshop.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userR;
	
	@PostConstruct //Se ejecuta después de haber inyectado las dependencias
	public void init() {
		
		save(new User("SergyLopez","123","sergiolopez@gmail.com",666777888));
		save(new User("AlbaLeon","1234","albaleon@gmail.com",666777999));
		save(new User("CarlosCuesta","12345","carloscuesta@gmail.com",666777000));
	}
	
	public Optional<User> findById(long idUser){
		return userR.findById(idUser);
	}
	
	public void save(User user) {
		userR.save(user);
	}
}
