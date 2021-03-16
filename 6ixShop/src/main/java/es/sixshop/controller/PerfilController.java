package es.sixshop.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import es.sixshop.model.Product;
import es.sixshop.model.User;
import es.sixshop.repository.ProductRepository;
import es.sixshop.repository.UserRepository;
import es.sixshop.service.ImageService;
import es.sixshop.service.UserService;



public class PerfilController {

	private static final String IMAGE_FOLDER = "images";
	
	/*@Autowired
	private UserRepository userR;*/
	
	@Autowired
	private UserService userS;
	
	@Autowired
	private ProductRepository productR;
	@Autowired
	private ImageService imageService;
	
	 @PostMapping("/profile")
	    public String newProduct(Model model, Product product, MultipartFile image) throws IOException{
	    	productR.save(product);
	    	
	    	imageService.saveImage(IMAGE_FOLDER, product.getIdProduct(), image);
	    	
	    	return "redirect:/profile";	
	    }
	
	 
	 @PutMapping("/profile")
	 public User replaceUser(@RequestBody User newUser, @PathVariable Long idUser, MultipartFile image) throws IOException {
		 
		newUser.setIdUser(idUser);
		 
		imageService.saveImage(IMAGE_FOLDER, newUser.getIdUser(), image);  //¿?¿?¿?¿? mirar luego 
		
		userS.replace(newUser);
	
		 return newUser;
	 }
	 }
