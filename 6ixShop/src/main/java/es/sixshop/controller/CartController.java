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
import org.springframework.web.bind.annotation.PathVariable;

import es.sixshop.model.Product;
import es.sixshop.model.User;
import es.sixshop.repository.UserRepository;
import es.sixshop.service.ProductService;

@Controller
public class CartController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserRepository userR;
	
	/*
	private Collection<Product> cart = new ArrayList<Product>();
    private int sum = 0;
	
    @GetMapping("/cart")
	public String seeCart(Model model, HttpSession session, HttpServletRequest request){
        //Comprueba si existe una sesión iniciada para cambiar el Header
        if(((Principal)request.getUserPrincipal())!=null) {
            String nickname = request.getUserPrincipal().getName();
            User user = userR.findByNickname(nickname).orElseThrow();

            model.addAttribute("user",user);
            model.addAttribute("nickname",user.getNickname());
        }
		model.addAttribute("products",cart);
		model.addAttribute("sum", sum);
		return "cart";
	}
	
	@GetMapping("/cart/{idProduct}")
	public String buyProduct(Model model, HttpSession session, HttpServletRequest request, @PathVariable long idProduct){
		boolean insert = true;
		Product prod = productService.findById(idProduct).orElseThrow();
        //Comprueba si existe una sesión iniciada para cambiar el Header
        if(((Principal)request.getUserPrincipal())!=null) {
            String nickname = request.getUserPrincipal().getName();
            User user = userR.findByNickname(nickname).orElseThrow();

            model.addAttribute("user",user);
            model.addAttribute("nickname",user.getNickname());
        }
		if(cart.isEmpty()){
			cart.add(prod);
			sum = sum + prod.getPrice();
		}
		for(Product p: cart ) {
			if(prod.getIdProduct() == p.getIdProduct()) {
				insert = false;
			}
		}
		if(insert == true) {
			cart.add(prod);
			sum = sum + prod.getPrice();
		}
		
		model.addAttribute("products",cart);
		model.addAttribute("sum", sum);
		return "cart";
	}
	
	@GetMapping("/checkout")
	public String seeCheckout(Model model, HttpSession session, HttpServletRequest request){
        //Comprueba si existe una sesión iniciada para cambiar el Header
        if(((Principal)request.getUserPrincipal())!=null) {
            String nickname = request.getUserPrincipal().getName();
            User user = userR.findByNickname(nickname).orElseThrow();
            model.addAttribute("user",user);
            model.addAttribute("nickname",user.getNickname());
        }

		model.addAttribute("cartItems",cart);
		model.addAttribute("sum", sum);
		//model.addAttribute("products",products);
		return "checkout";
	}
	
	@GetMapping("/cardPayment")
	public String seePayment(Model model, HttpSession session, HttpServletRequest request){
		
		//model.addAttribute("cartItems",carrito);
		//model.addAttribute("sum", sum);
		//model.addAttribute("products",products);
		return "cardPayment";
	}
	
	@GetMapping("/cardPayment/return")
	public String seePayment2(Model model, HttpSession session, HttpServletRequest request){
        if(((Principal)request.getUserPrincipal())!=null) {
            String nickname = request.getUserPrincipal().getName();
            User user = userR.findByNickname(nickname).orElseThrow();
            model.addAttribute("user",user);
            model.addAttribute("nickname",user.getNickname());
        }
		//model.addAttribute("cartItems",carrito);
		//model.addAttribute("sum", sum);
		//model.addAttribute("products",products);
		return "profile";
	}
	*/
}
