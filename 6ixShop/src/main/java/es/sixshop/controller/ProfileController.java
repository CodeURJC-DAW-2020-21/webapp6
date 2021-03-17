package es.sixshop.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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



public class ProfileController {

	private static final String IMAGE_FOLDER = "src/main/resources/img/imagenes/product";
	private static final String PROFILE_FOLDER = "src/main/resources/img/imagenes/profile";
	
	@Autowired
	private UserRepository userR;
	
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
	 
		imageService.saveImage(PROFILE_FOLDER, newUser.getIdUser(), image);  //¿?¿?¿?¿? mirar luego 
	
		userS.replace(newUser);

		return newUser;
	}
	
	@GetMapping("/profile")
	public String showProfile(Model model, HttpServletRequest request) {		
		//Datos usuario
		String nickname = request.getUserPrincipal().getName();
		User user = userR.findByNickname(nickname).orElseThrow();
		
		model.addAttribute("user",user);
		model.addAttribute("nickname",user.getNickname());
		model.addAttribute("mail",user.getMail());
		model.addAttribute("perfil",true);
		
		//Datos productos subidos
		Optional<Product> products = productR.findByidUser(user.getIdUser());
		model.addAttribute("products",products);
		
		return "profile";
	}
}
