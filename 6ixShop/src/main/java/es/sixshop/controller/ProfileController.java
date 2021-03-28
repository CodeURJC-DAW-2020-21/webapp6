package es.sixshop.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.sixshop.service.ProductService;
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
		Collection<Product> products = productR.findByidUser(user.getIdUser());
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

		product.setIdUser(user.getIdUser());
		productS.save(product);
		
		model.addAttribute("product", product.getIdProduct());
		
		return "redirect:/profile";
    }
}
