package es.sixshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sixshop.model.User;
import es.sixshop.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userR;
	
	public Optional<User> findById(long idUser){
		return userR.findById(idUser);
	}
	
	public List<User> findAll() {
		return userR.findAll();
	}
	
	public void save(User user) {
		userR.save(user);
	}
	
	public void delete(long id) {
		userR.deleteById(id);
	}
	
	public void replace(User updatedUser) {
		userR.findById(updatedUser.getIdUser()).orElseThrow();
		userR.save(updatedUser);	
	}
}