package es.sixshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import es.sixshop.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userR;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/sing_in")
	public String signin() {
		return"sign_in";
	}
	
}
