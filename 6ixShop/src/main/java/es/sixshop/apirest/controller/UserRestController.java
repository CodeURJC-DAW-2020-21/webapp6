package es.sixshop.apirest.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.sixshop.apirest.detail.UserDetail;
import es.sixshop.model.User;
import es.sixshop.service.UserService;

@RestController
public class UserRestController {
	
	@Autowired
	private UserService userS;
	
	@JsonView(UserDetail.class)
	@GetMapping("/api/users/")
	public Collection<User> getUsers(){
		return userS.findAll();
	}
}
