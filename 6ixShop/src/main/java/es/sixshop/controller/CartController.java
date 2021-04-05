package es.sixshop.controller;

import java.security.Principal;
import java.sql.Date;
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
            Request requestUser = requestS.findByBuyerUserAndStatus(user,"Cart");
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
	
	@GetMapping("/cart/{idProduct}")
	public String addCartProduct(Model model, HttpSession session, HttpServletRequest request, @PathVariable long idProduct){
		String nickname = request.getUserPrincipal().getName();
        User user = userR.findByNickname(nickname).orElseThrow();
        Product prod = productS.findById(idProduct).orElseThrow();
        Request objRequest = requestS.findByBuyerUserAndStatus(user, "Cart");
        RequestDetail requestDetail = new RequestDetail(objRequest,prod,prod.getPrice());
        objRequest.setRequestDetail(requestDetail);
        
        requestDetailS.save(requestDetail);
        requestS.save(objRequest);
		
		return "redirect:/cart";
	}
	
	/*
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
	}*/
	
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
        requestUser.setDate(Date.valueOf(LocalDate.now()));
        
        /*Collection<RequestDetail> requestDetail = requestDetailS.findByRequest(requestUser);
        //MODIFICAR EL RATING Y LA DESCRIPCION
        for (RequestDetail objRequestDetail : requestDetail) {
        	objRequestDetail.setRating(5);
        	requestDetailS.save(objRequestDetail);
        }*/
        requestS.save(requestUser);
        
        /* Se crea otro PEDIDO como CARRITO vacío */
        Request newRequest = new Request(user);
        requestS.save(newRequest);
	
		return "redirect:/profile";
	}
}
