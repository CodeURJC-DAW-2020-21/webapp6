package es.sixshop.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.sixshop.model.Product;
import es.sixshop.model.User;
import es.sixshop.repository.UserRepository;
import es.sixshop.service.ProductService;

@Controller
public class CategoryController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserRepository userR;
	
	@GetMapping("/category")
	public String seeCategories(Model model, HttpSession session, HttpServletRequest request){
        //Comprueba si existe una sesión iniciada para cambiar el Header
        if(((Principal)request.getUserPrincipal())!=null) {
            String nickname = request.getUserPrincipal().getName();
            User user = userR.findByNickname(nickname).orElseThrow();

            model.addAttribute("user",user);
            model.addAttribute("nickname",user.getNickname());
        }
		Collection<Product> products = productService.findAll();
		model.addAttribute("categoryName","Categories");
		model.addAttribute("products",products);
		return "category";
	}
	
	//Estos métodos hay que unificarlos en uno (de momento lo tenemos asi para que )
	@GetMapping("/category/Movies")
	public String seeMovies(Model model, HttpSession session, HttpServletRequest request){
        //Comprueba si existe una sesión iniciada para cambiar el Header
        if(((Principal)request.getUserPrincipal())!=null) {
            String nickname = request.getUserPrincipal().getName();
            User user = userR.findByNickname(nickname).orElseThrow();

            model.addAttribute("user",user);
            model.addAttribute("nickname",user.getNickname());
        }
		Collection<Product> products = productService.findAll();
		//products = productService.filterByCategory(products, "Movies");
		model.addAttribute("categoryName","Movies");
		model.addAttribute("products",products);
		return "category";
	}
		
		@GetMapping("/category/TVSeries")
		public String seeTVShow(Model model, HttpSession session, HttpServletRequest request){
	        //Comprueba si existe una sesión iniciada para cambiar el Header
	        if(((Principal)request.getUserPrincipal())!=null) {
	            String nickname = request.getUserPrincipal().getName();
	            User user = userR.findByNickname(nickname).orElseThrow();

	            model.addAttribute("user",user);
	            model.addAttribute("nickname",user.getNickname());
	        }
			Collection<Product> products = productService.findAll();
			//products = productService.filterByCategory(products, "TVSeries");
			products = productService.findBycategory("TVSeries");
			model.addAttribute("categoryName","TVSeries");
			model.addAttribute("products",products);
			return "category";
		}
		
		@GetMapping("/category/Music")
		public String seeMusic(Model model, HttpSession session, HttpServletRequest request){
	        //Comprueba si existe una sesión iniciada para cambiar el Header
	        if(((Principal)request.getUserPrincipal())!=null) {
	            String nickname = request.getUserPrincipal().getName();
	            User user = userR.findByNickname(nickname).orElseThrow();

	            model.addAttribute("user",user);
	            model.addAttribute("nickname",user.getNickname());
	        }
			Collection<Product> products = productService.findAll();
			//products = productService.filterByCategory(products, "Music");
			model.addAttribute("categoryName","Music");
			model.addAttribute("products",products);
			return "category";
		}
		
		@GetMapping("/category/Photography")
		public String seePhotography(Model model, HttpSession session, HttpServletRequest request){
	        //Comprueba si existe una sesión iniciada para cambiar el Header
	        if(((Principal)request.getUserPrincipal())!=null) {
	            String nickname = request.getUserPrincipal().getName();
	            User user = userR.findByNickname(nickname).orElseThrow();

	            model.addAttribute("user",user);
	            model.addAttribute("nickname",user.getNickname());
	        }
			Collection<Product> products = productService.findAll();
			//products = productService.filterByCategory(products, "Photography");
			model.addAttribute("categoryName","Photography");
			model.addAttribute("products",products);
			return "category";
		}
		
		@GetMapping("/category/Comics")
		public String seeComics(Model model, HttpSession session, HttpServletRequest request){
	        //Comprueba si existe una sesión iniciada para cambiar el Header
	        if(((Principal)request.getUserPrincipal())!=null) {
	            String nickname = request.getUserPrincipal().getName();
	            User user = userR.findByNickname(nickname).orElseThrow();

	            model.addAttribute("user",user);
	            model.addAttribute("nickname",user.getNickname());
	        }
			Collection<Product> products = productService.findAll();
			//products = productService.filterByCategory(products, "Comics");
			model.addAttribute("categoryName","Comics");
			model.addAttribute("products",products);
			return "category";
		}
		
		@GetMapping("/category/Custom")
		public String seeCustom(Model model, HttpSession session, HttpServletRequest request){
	        //Comprueba si existe una sesión iniciada para cambiar el Header
	        if(((Principal)request.getUserPrincipal())!=null) {
	            String nickname = request.getUserPrincipal().getName();
	            User user = userR.findByNickname(nickname).orElseThrow();

	            model.addAttribute("user",user);
	            model.addAttribute("nickname",user.getNickname());
	        }
			Collection<Product> products = productService.findAll();
			//products = productService.filterByCategory(products, "Custom");
			model.addAttribute("categoryName","Custom");
			model.addAttribute("products",products);
			return "category";
		}
}
