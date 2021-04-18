package es.sixshop.controller;

import java.security.Principal;

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
import es.sixshop.service.ProductService;
import es.sixshop.service.UserService;

@Controller
public class CategoryController {
	
	@Autowired
	private ProductService productS;
	
	@Autowired
	private UserService userS;
	
	@GetMapping("/category/{category}")
	public String showCategory(Model model, HttpSession session, HttpServletRequest request, Pageable pageable, @PathVariable String category){
		// Check if there is a session started to change the Header
        if(((Principal)request.getUserPrincipal())!=null) {
            String nickname = request.getUserPrincipal().getName();
            User user = userS.findByNickname(nickname).orElseThrow();

            model.addAttribute("user",user);
            model.addAttribute("nickname",user.getNickname());
        }
        
        Page<Product> productsCategory = productS.findByCategoryAndVisible(category, pageable);
        model.addAttribute("productsCategory", productsCategory);
        model.addAttribute("categoryName",category);
        // The first page shown is subtracted
        model.addAttribute("totalPageAll",(productsCategory.getTotalPages()-1));
		
		return "category";
	}
	
	@GetMapping("/category/loadMoreCategories/{category}")
	public String showLoadMoreCategory(Model model, HttpSession session, Pageable pageable, @PathVariable String category) {
		Page<Product> productsCategory = productS.findByCategoryAndVisible(category, pageable);			
		// Load the next page of the complete products
		model.addAttribute("productsCategory", productsCategory);

		return "loadMoreCategories";
	}
}
