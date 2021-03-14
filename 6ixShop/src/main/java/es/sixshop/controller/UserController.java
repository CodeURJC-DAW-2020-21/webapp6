package es.sixshop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.sixshop.model.User;
import es.sixshop.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userR;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/sign_in")
	public String signin() {
		return"sign_in";
	}
	
	@GetMapping("/profile")
	public String showProfile(Model model, HttpServletRequest request) {
		String nickname = request.getUserPrincipal().getName();
		User user = userR.findByNickname(nickname).orElseThrow();
		
		model.addAttribute("user",user);
		model.addAttribute("nickname",user.getNickname());
		model.addAttribute("perfil",true);
		
		return "profile";
	}
    
    @PostMapping("/sign_in")
    public String newUser(Model model, User user) {
    	userR.save(user);
    	
    	return "profile";	
    }
}
