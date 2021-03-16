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
		//Collection<Product> products = productService.findAll();
		//session.getAttribute("idProduct");
		//model.addAttribute("productR",products);
		Product prod = productService.findById(idProduct).orElseThrow();
		model.addAttribute("productR",prod);
		return "single-product";
	}
	
	@GetMapping("/cart")
	public String seeCart(Model model, HttpSession session){
		//session.getAttribute("idProduct");
		//model.addAttribute("productR",products);
		//Product prod = productService.findById(idProduct).orElseThrow();
		//carrito.add(prod);
		//model.addAttribute("productR",carrito);
		return "cart";
	}
	
	
	@GetMapping("/cart/{idProduct}")
	public String buyProduct(Model model, HttpSession session, @PathVariable long idProduct){
		//session.getAttribute("idProduct");
		//model.addAttribute("productR",products);
		Product prod = productService.findById(idProduct).orElseThrow();
		
		
		for(Product p : carrito) {
			if(p.getIdProduct() != prod.getIdProduct()){
				carrito.add(prod);
				sum = sum + prod.getPrice();
			}
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
		model.addAttribute("products",products);
		return "category";
	}
}
