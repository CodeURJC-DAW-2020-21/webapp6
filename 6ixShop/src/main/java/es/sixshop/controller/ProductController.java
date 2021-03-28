package es.sixshop.controller;

import java.security.Principal;
import java.sql.SQLException;
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
import es.sixshop.repository.UserRepository;
import es.sixshop.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productS;
	
	@Autowired
	private UserRepository userR;
	
	
	@GetMapping("/")
    public String showProduct(Model model, HttpSession session, HttpServletRequest request){
        //Comprueba si existe una sesión iniciada para cambiar el Header
        if(((Principal)request.getUserPrincipal())!=null) {
            String nickname = request.getUserPrincipal().getName();
            User user = userR.findByNickname(nickname).orElseThrow();

            model.addAttribute("user",user);
            model.addAttribute("nickname",user.getNickname());
        }

        //Carga todos los productos
        Collection<Product> products = productS.findAll();
        model.addAttribute("products",products);

        return "index";
    }
	
	@GetMapping("/single-product/{idProduct}")
	public String showSingleProduct(Model model, HttpSession session, HttpServletRequest request, @PathVariable long idProduct){
        //Comprueba si existe una sesión iniciada para cambiar el Header
        if(((Principal)request.getUserPrincipal())!=null) {
            String nickname = request.getUserPrincipal().getName();
            User user = userR.findByNickname(nickname).orElseThrow();

            model.addAttribute("user",user);
            model.addAttribute("nickname",user.getNickname());
        }
        
		Product product = productS.findById(idProduct).orElseThrow();
		model.addAttribute("product",product);
		return "single-product";
	}
	
	@GetMapping("/{idProduct}/image")
	public ResponseEntity<Object> downloadImage(@PathVariable long idProduct) throws SQLException {

		Optional<Product> product = productS.findById(idProduct);
		if (product.isPresent() && product.get().getImageFile() != null) {

			Resource file = new InputStreamResource(product.get().getImageFile().getBinaryStream());

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
					.contentLength(product.get().getImageFile().length()).body(file);

		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
