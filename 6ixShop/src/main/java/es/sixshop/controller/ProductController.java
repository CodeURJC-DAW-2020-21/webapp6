package es.sixshop.controller;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.sixshop.model.Product;
import es.sixshop.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	
	@GetMapping("/")
	public String showProduct(Model model, HttpSession session){
		Collection<Product> products = productService.findAll();
		
		model.addAttribute("productR",products);
		
		return "index";
	}
}
