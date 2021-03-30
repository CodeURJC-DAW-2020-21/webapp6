package es.sixshop.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import es.sixshop.service.ProductService;
import es.sixshop.model.Product;
import es.sixshop.model.User;
import es.sixshop.repository.ProductRepository;
import es.sixshop.repository.UserRepository;

@Controller
public class ProfileController {	
	@Autowired
	private UserRepository userR;
	
	@Autowired
	private ProductRepository productR;
	
	@Autowired
	private ProductService productS;
	
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
		//Collection<Product> products = productR.findByidUser(user.getIdUser());
		Collection<Product> products = productR.findByUser(user);
		model.addAttribute("products",products);
		
		return "profile";
	}
	
	@PostMapping("/profile")
    public String newProduct(HttpServletRequest request, Model model, Product product, MultipartFile imageField) throws IOException {
		String nickname = request.getUserPrincipal().getName();
        User user = userR.findByNickname(nickname).orElseThrow();
    	
		if (!imageField.isEmpty()) {
			product.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			product.setImage(true);
		}

		//product.setIdUser(user.getIdUser());
		product.setUser(user);
		productS.save(product);
		
		model.addAttribute("product", product.getIdProduct());
		
		return "redirect:/profile";
    }
}
