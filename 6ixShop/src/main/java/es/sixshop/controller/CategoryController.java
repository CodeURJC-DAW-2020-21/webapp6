package es.sixshop.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import es.sixshop.model.Product;
import es.sixshop.model.User;
import es.sixshop.repository.UserRepository;
import es.sixshop.service.ProductService;

@Controller
public class CategoryController {
	
	@Autowired
	private ProductService productS;
	
	@Autowired
	private UserRepository userR;
	
	/*
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
	}*/
	
	@GetMapping("/category/{category}")
	public String showCategory(Model model, HttpSession session, HttpServletRequest request, Pageable pageable, @PathVariable String category){
        //Comprueba si existe una sesión iniciada para cambiar el Header
        if(((Principal)request.getUserPrincipal())!=null) {
            String nickname = request.getUserPrincipal().getName();
            User user = userR.findByNickname(nickname).orElseThrow();

            model.addAttribute("user",user);
            model.addAttribute("nickname",user.getNickname());
        }
        
        Page<Product> productsCategory = productS.findBycategory(category, pageable);
        model.addAttribute("productsCategory", productsCategory);
        model.addAttribute("categoryName",category);
        //Se resta la primera página que se muestra
        model.addAttribute("totalPageAll",(productsCategory.getTotalPages()-1));
		
		return "category";
	}
	
	@GetMapping("/category/loadMoreCategories/{category}")
	public String showLoadMoreCategory(Model model, HttpSession session, Pageable pageable, @PathVariable String category) {
		Page<Product> productsCategory = productS.findBycategory(category, pageable);			
		//Carga la siguiente página de los productos completos
		model.addAttribute("productsCategory", productsCategory);

		return "loadMoreCategories";
	}
}
