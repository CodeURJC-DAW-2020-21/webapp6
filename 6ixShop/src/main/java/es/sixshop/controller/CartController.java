package es.sixshop.controller;

import java.security.Principal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import es.sixshop.model.Product;
import es.sixshop.model.User;
import es.sixshop.model.Product;
import es.sixshop.model.Request;
import es.sixshop.model.RequestDetail;
import es.sixshop.repository.UserRepository;
import es.sixshop.service.ProductService;
import es.sixshop.service.RequestDetailService;
import es.sixshop.service.RequestService;

@Controller
public class CartController {
	
	@Autowired
	private ProductService productS;
	
	@Autowired
	private RequestService requestS;
	
	@Autowired
	private RequestDetailService requestDetailS;
	
	@Autowired
	private UserRepository userR;
	
	@GetMapping("/cart")
	public String showCart(Model model, HttpSession session, HttpServletRequest request){
		int totalPrice = 0;
		
		//Comprueba si existe una sesión iniciada para cambiar el Header
        if(((Principal)request.getUserPrincipal())!=null) {
            String nickname = request.getUserPrincipal().getName();
            User user = userR.findByNickname(nickname).orElseThrow();
            
            //Carga el carrito
            Request requestUser = requestS.findByBuyerUserAndStatus(user,"cart");
            Collection<Product> products = requestDetailS.findProductOfRequestDetail(requestUser);
            
            //Se calcula el total del precio de los productos
            for (Product product : products) {
            	totalPrice+= product.getPrice();
            }

            model.addAttribute("user",user);
            model.addAttribute("nickname",user.getNickname());
            model.addAttribute("requestUser",requestUser);
            model.addAttribute("products", products);
            model.addAttribute("idRequest",requestUser.getIdRequest());
        }
        
        model.addAttribute("totalPrice",totalPrice);
		
		return "cart";
	}
	
	@GetMapping("/checkout/{idRequest}")
	public String showCheckout(Model model, HttpSession session, HttpServletRequest request, @PathVariable Long idRequest){
		int totalPrice = 0;
		
        //Comprueba si existe una sesión iniciada para cambiar el Header
        if(((Principal)request.getUserPrincipal())!=null) {
            String nickname = request.getUserPrincipal().getName();
            User user = userR.findByNickname(nickname).orElseThrow();
            
            Request requestUser = requestS.findById(idRequest).orElseThrow();
            Collection<Product> products = requestDetailS.findProductOfRequestDetail(requestUser);
            //Se calcula el total del precio de los productos
            for (Product product : products) {
            	totalPrice+= product.getPrice();
            }
            
            model.addAttribute("user",user);
            model.addAttribute("nickname",user.getNickname());
            model.addAttribute("requestUser",requestUser);
            model.addAttribute("products", products);
        }
        
        model.addAttribute("totalPrice",totalPrice);
        
		return "checkout";
	}
	
	@GetMapping("/cardPayment/{idRequest}")
	public String showCardPayment(Model model, HttpSession session, HttpServletRequest request, @PathVariable Long idRequest){
		model.addAttribute("idRequest", idRequest);
		
		return "cardPayment";
	}
	
	@GetMapping("/cardPayment/requestCompleted/{idRequest}")
	public String requestCompleted(Model model, HttpSession session, HttpServletRequest request, @PathVariable Long idRequest){
		String nickname = request.getUserPrincipal().getName();
        User user = userR.findByNickname(nickname).orElseThrow();
		
        /* Se guarda el PEDIDO como PAGADO y se saca del carrito */
        Request requestUser = requestS.findById(idRequest).orElseThrow();
        requestUser.setStatus("PAID");
        requestUser.setDate(LocalDate.now());
        
        Collection<RequestDetail> requestDetail = requestDetailS.findByRequest(requestUser);
        //MODIFICAR EL RATING Y LA DESCRIPCION
        for (RequestDetail objRequestDetail : requestDetail) {
        	objRequestDetail.setRating(5);
        	objRequestDetail.setDescRating("Description Rating");
        	requestDetailS.save(objRequestDetail);
        }
        requestS.save(requestUser);
        
        /* Se crea otro PEDIDO como CARRITO vacío */
        Request newRequest = new Request(user);
        requestS.save(newRequest);
	
		return "redirect:/profile";
	}
	
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
