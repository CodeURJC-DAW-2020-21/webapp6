package es.sixshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.sixshop.model.User;
import es.sixshop.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userR;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/sign_in")
	public String signin() {
		return"sign_in";
	}
    
    @PostMapping("/sign_in")
    public String newUser(Model model, User user) {
    	userR.save(user);
    	
    	return "login";	
    }
}
