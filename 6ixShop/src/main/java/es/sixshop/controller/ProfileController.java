package es.sixshop.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import es.sixshop.service.ImageService;
import es.sixshop.model.Product;
import es.sixshop.model.User;
import es.sixshop.repository.ProductRepository;
import es.sixshop.repository.UserRepository;
import es.sixshop.service.UserService;

@Controller
public class ProfileController {
	private static final String POSTS_FOLDER = "product";
	
	@Autowired
	private UserRepository userR;
	
	@Autowired
	private UserService userS;
	
	@Autowired
	private ProductRepository productR;
	
	@Autowired
	private ImageService imageService;
	
	@GetMapping("/profile")
	public String showProfile(Model model, HttpServletRequest request) {	
		
		//Datos usuario
		String nickname = request.getUserPrincipal().getName();
		User user = userR.findByNickname(nickname).orElseThrow();
		
		model.addAttribute("user",user);
		model.addAttribute("nickname",user.getNickname());
		model.addAttribute("mail",user.getMail());
		model.addAttribute("profile",true);
		
		
		//Datos productos subidos
		Collection<Product> products = productR.findByidUser(user.getIdUser());
		model.addAttribute("products",products);
		
		return "profile";
	}
	
	
	@PostMapping("/profile")
    public String newProduct(HttpServletRequest request, Product product, MultipartFile image) throws IOException{
		String nickname = request.getUserPrincipal().getName();
        User user = userR.findByNickname(nickname).orElseThrow();
        
		product.setIdUser(user.getIdUser());
    	productR.save(product);
    	imageService.saveImage(POSTS_FOLDER, product.getIdProduct(), image);
    	
    	
    	return "redirect:/profile";	
    }
}
