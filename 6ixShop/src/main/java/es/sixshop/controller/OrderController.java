package es.sixshop.controller;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.sixshop.model.Order;
import es.sixshop.model.User;
import es.sixshop.repository.OrderRepository;
import es.sixshop.repository.UserRepository;

@Controller
public class OrderController {
	@Autowired
	private UserRepository userR;
	
	@Autowired
	private OrderRepository orderR;
	
    
    @PostMapping("/cardPayment/OK")
    public String newOrder(Model model, HttpSession session, HttpServletRequest request) {
    	if(((Principal)request.getUserPrincipal())!=null) {
            String nickname = request.getUserPrincipal().getName();
            User user = userR.findByNickname(nickname).orElseThrow();
            
            model.addAttribute("user",user);
            model.addAttribute("nickname",user.getNickname());
            
            
        	Date date = new Date();
        	Order order = new Order(user.getIdUser(),date,"ESTADO");
            
        	orderR.save(order);
        }

    	
    	
    	return "profile";	
    }

}
