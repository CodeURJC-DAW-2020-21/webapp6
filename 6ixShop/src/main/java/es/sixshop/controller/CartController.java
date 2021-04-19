package es.sixshop.controller;

import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
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
import es.sixshop.model.Request;
import es.sixshop.model.RequestDetail;
import es.sixshop.repository.UserRepository;
import es.sixshop.service.ProductService;
import es.sixshop.service.RequestDetailService;
import es.sixshop.service.RequestService;
import es.sixshop.service.UserService;

@Controller
public class CartController {
	
	@Autowired
	private ProductService productS;
	
	@Autowired
	private RequestService requestS;
	
	@Autowired
	private RequestDetailService requestDetailS;
	
	@Autowired
	private UserService userS;
	
	@GetMapping("/cart")
	public String showCart(Model model, HttpSession session, HttpServletRequest request){
		int totalPrice = 0;
		
		// Check if there is a session started to change the Header
        if(((Principal)request.getUserPrincipal())!=null) {
            String nickname = request.getUserPrincipal().getName();
            User user = userS.findByNickname(nickname).orElseThrow();
            
            // Load the cart
            Request requestUser = requestS.findByBuyerUserAndStatus(user,"Cart");
            Collection<Product> products = requestDetailS.findProductOfRequestDetail(requestUser);
            
            // The total price of the products is calculated
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
        User user = userS.findByNickname(nickname).orElseThrow();
        Product prod = productS.findById(idProduct).orElseThrow();
        Request objRequest = requestS.findByBuyerUserAndStatus(user, "Cart");
        RequestDetail requestDetail = new RequestDetail(objRequest,prod,prod.getPrice());
        objRequest.setRequestDetail(requestDetail);
        
        requestDetailS.save(requestDetail);
        requestS.save(objRequest);
		
		return "redirect:/cart";
	}
	
	@GetMapping("/cardPayment/{idRequest}")
	public String showCardPayment(Model model, HttpSession session, HttpServletRequest request, @PathVariable Long idRequest){
		model.addAttribute("idRequest", idRequest);
		
		return "cardPayment";
	}
	
	@GetMapping("/cardPayment/requestCompleted/{idRequest}")
	public String requestCompleted(Model model, HttpSession session, HttpServletRequest request, @PathVariable Long idRequest){
		String nickname = request.getUserPrincipal().getName();
        User user = userS.findByNickname(nickname).orElseThrow();
		
        /* The ORDER is saved as PAID and removed from the cart */
        Request requestUser = requestS.findById(idRequest).orElseThrow();
        requestUser.setStatus("PAID");
        requestUser.setDate(Date.valueOf(LocalDate.now()));
        
        requestS.save(requestUser);
        
        /* Another ORDER is created as an empty CART */
        Request newRequest = new Request(user);
        requestS.save(newRequest);
	
		return "redirect:/profile";
	}
}
