package es.sixshop.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import es.sixshop.model.Product;
import es.sixshop.model.User;
import es.sixshop.repository.ProductRepository;
import es.sixshop.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userR;
	
	@Autowired
	private ProductRepository productR;
	
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
