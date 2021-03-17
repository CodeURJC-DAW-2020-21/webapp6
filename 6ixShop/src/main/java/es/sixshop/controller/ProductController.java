package es.sixshop.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import es.sixshop.model.Product;
import es.sixshop.repository.UserRepository;
import es.sixshop.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserRepository userR;
	
	private Collection<Product> carrito = new ArrayList<Product>();
	private int sum = 0;
	
	
	private Collection<Product> filterById(Collection<Product> products, String categorie) {
		
		Collection<Product> productsFiltered = new ArrayList<Product>();;
		
		for(Product p: products ) {
			if(p.getCategory() == categorie) {
				productsFiltered.add(p);
			}
		}
		
		return productsFiltered;
	}
	
	@GetMapping("/")
	public String showProduct(Model model, HttpSession session){
		//String nickname = request.getUserPrincipal().getName();
		//User user = userR.findByNickname(nickname).orElseThrow();
		//model.addAttribute("user",user);
		//model.addAttribute("nickname",user.getNickname());
		
		Collection<Product> products = productService.findAll();
		
		model.addAttribute("products",products);
		
		return "index";
	}
	
	@GetMapping("/single-product/{idProduct}")
	public String showSingleProduct(Model model, HttpSession session, @PathVariable long idProduct){

		Product prod = productService.findById(idProduct).orElseThrow();
		model.addAttribute("productR",prod);
		return "single-product";
	}
	
	@GetMapping("/cart")
	public String seeCart(Model model, HttpSession session){
		
		model.addAttribute("products",carrito);
		model.addAttribute("sum", sum);
		return "cart";
	}
	
	
	@GetMapping("/cart/{idProduct}")
	public String buyProduct(Model model, HttpSession session, @PathVariable long idProduct){
		boolean insert = true;
		Product prod = productService.findById(idProduct).orElseThrow();
		
		if(carrito.size() <= 0 ){
			carrito.add(prod);
			sum = sum + prod.getPrice();
		}
		for(Product p: carrito ) {
			if(prod.getIdProduct() == p.getIdProduct()) {
				insert = false;
			}
		}
		if(insert == true) {
			carrito.add(prod);
			sum = sum + prod.getPrice();
		}
		
		model.addAttribute("products",carrito);
		model.addAttribute("sum", sum);
		return "cart";
	}
	
	@GetMapping("/category")
	public String seeCategories(Model model, HttpSession session){
		//session.getAttribute("idProduct");
		//model.addAttribute("productR",products);
		Collection<Product> products = productService.findAll();
		model.addAttribute("categoryName","Categories");
		model.addAttribute("products",products);
		return "category";
	}
	
	@GetMapping("/category/Movies")
	public String seeMovies(Model model, HttpSession session){
		//session.getAttribute("idProduct");
		//model.addAttribute("productR",products);
		Collection<Product> products = productService.findAll();
		products = filterById(products, "Movies");
		model.addAttribute("categoryName","Movies");
		model.addAttribute("products",products);
		return "category";
	}
	
	@GetMapping("/category/{TVSeries}")
	public String seeTVShow(Model model, HttpSession session){
		//session.getAttribute("idProduct");
		//model.addAttribute("productR",products);
		Collection<Product> products = productService.findAll();
		products = filterById(products, "TVSeries");
		model.addAttribute("categoryName","TVSeries");
		model.addAttribute("products",products);
		return "category";
	}
	
	@GetMapping("/category/Music")
	public String seeMusic(Model model, HttpSession session){
		//session.getAttribute("idProduct");
		//model.addAttribute("productR",products);
		Collection<Product> products = productService.findAll();
		products = filterById(products, "Music");
		model.addAttribute("categoryName","Music");
		model.addAttribute("products",products);
		return "category";
	}
	
	@GetMapping("/category/Photography")
	public String seePhotography(Model model, HttpSession session){
		//session.getAttribute("idProduct");
		//model.addAttribute("productR",products);
		Collection<Product> products = productService.findAll();
		products = filterById(products, "Photography");
		model.addAttribute("categoryName","Photography");
		model.addAttribute("products",products);
		return "category";
	}
	@GetMapping("/category/Comics")
	public String seeComics(Model model, HttpSession session){
		//session.getAttribute("idProduct");
		//model.addAttribute("productR",products);
		Collection<Product> products = productService.findAll();
		products = filterById(products, "Comics");
		model.addAttribute("categoryName","Comics");
		model.addAttribute("products",products);
		return "category";
	}
	
	@GetMapping("/category/Custom")
	public String seeCustom(Model model, HttpSession session){
		//session.getAttribute("idProduct");
		//model.addAttribute("productR",products);
		Collection<Product> products = productService.findAll();
		products = filterById(products, "Custom");
		model.addAttribute("categoryName","Custom");
		model.addAttribute("products",products);
		return "category";
	}
}
