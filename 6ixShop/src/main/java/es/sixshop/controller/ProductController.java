package es.sixshop.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.sixshop.Application;
import es.sixshop.model.Product;
import es.sixshop.model.User;
import es.sixshop.service.ProductService;
import es.sixshop.service.UserService;

@Controller
public class ProductController {
	
	private int ID_ADMIN = 1;
	
	@Autowired
	private ProductService productS;
	
	@Autowired
	private UserService userS;
	
	
	@GetMapping("/")
    public String showProduct(Model model, HttpSession session, HttpServletRequest request, Pageable pageable){
		// Check if there is a session started to change the Header
        if(((Principal)request.getUserPrincipal())!=null) {
            String nickname = request.getUserPrincipal().getName();
            User user = userS.findByNickname(nickname).orElseThrow();

            model.addAttribute("user",user);
            model.addAttribute("nickname",user.getNickname());
        }

        // Load the first page of the complete products
        Page<Product> productsAll = productS.findAll(pageable);
		model.addAttribute("productsAll", productsAll);
		// The first page shown is subtracted
		model.addAttribute("totalPageAll",(productsAll.getTotalPages()-1));
		
		
		// Load the first page of the products by rating
        Page<Product> productsRating = productS.findByRating(pageable);
		model.addAttribute("productsRating", productsRating);
		// The first page shown is subtracted
		model.addAttribute("totalPageRating",(productsRating.getTotalPages()-1));

        return "index";
    }
	
	@GetMapping("/loadMoreAll")
	public String showLoadMoreAll(Model model, HttpSession session, Pageable pageable) {
		Page<Product> productsAll = productS.findAll(pageable);			
		//Carga la siguiente página de los productos completos
		model.addAttribute("productsAll", productsAll);

		return "loadMoreAll";
	}
	
	@GetMapping("/loadMoreRating")
	public String showLoadMoreRating(Model model, HttpSession session, Pageable pageable) {
		Page<Product> productsRating = productS.findByRating(pageable);			
		// Load the next page of the complete products
		model.addAttribute("productsRating", productsRating);

		return "loadMoreRating";
	}
	
	@GetMapping("/single-product/{idProduct}")
	public String showSingleProduct(Model model, HttpSession session, HttpServletRequest request, @PathVariable long idProduct){	
		Product product = productS.findById(idProduct).orElseThrow();
		model.addAttribute("product",product);
		
		// Check if there is a session started to change the Header
        if(((Principal)request.getUserPrincipal())!=null) {
            String nickname = request.getUserPrincipal().getName();
            User user = userS.findByNickname(nickname).orElseThrow();

            model.addAttribute("user",user);
            model.addAttribute("nickname",user.getNickname());
            
            if((user.getIdUser() == product.getUser().getIdUser()) || (ID_ADMIN == user.getIdUser())) {
    			model.addAttribute("edit",true);
    		}
        }
        
		return "single-product";
	}
	
	@GetMapping("/edit-product/{idProduct}")
	public String showEditProduct(Model model, HttpSession session, HttpServletRequest request, @PathVariable long idProduct){
		// Check if there is a session started to change the Header
        if(((Principal)request.getUserPrincipal())!=null) {
            String nickname = request.getUserPrincipal().getName();
            User user = userS.findByNickname(nickname).orElseThrow();

            model.addAttribute("user",user);
            model.addAttribute("nickname",user.getNickname());
            
            Product product = productS.findById(idProduct).orElseThrow();
    		long idUsuarioProduct = product.getUser().getIdUser();
    		
    		if((idUsuarioProduct==user.getIdUser()) || (ID_ADMIN==user.getIdUser())) {
    			model.addAttribute("product",product);
    			return "edit-product";
    		} else {
    			return "redirect:/";
    		}
        }
        return "redirect:/";
	}
	
	@PostMapping("/edit-product/{idProduct}")
	public String editProduct(HttpServletRequest request, Model model, Product product)
			throws IOException {
		
		if(((Principal)request.getUserPrincipal())!=null) {
            String nickname = request.getUserPrincipal().getName();
            User user = userS.findByNickname(nickname).orElseThrow();
            
            Product original = productS.findById(product.getIdProduct()).orElseThrow();
            long idUsuarioProduct = original.getUser().getIdUser();
    		if((idUsuarioProduct==user.getIdUser()) || (ID_ADMIN==user.getIdUser())) {
    			product.setCategory(original.getCategory());
    			product.setImageFile(original.getImageFile());
    			product.setImage(original.isImage());
    			product.setRating(original.getRating());
    			product.setUser(user);
    			productS.update(product);
    				
    			return "redirect:/single-product/"+product.getIdProduct();
    		} else {
    			return "redirect:/";
    		}
		}
		
		return "index";
	}
	
	@PostMapping("/delete-product/{idProduct}")
	public String deleteProduct(HttpServletRequest request, Model model, Product product)
			throws IOException {
		
		productS.delete(product.getIdProduct());

		return "redirect:/";
	}
	
	@GetMapping("/{idProduct}/image")
	public ResponseEntity<Object> downloadImage(@PathVariable long idProduct) throws SQLException, MalformedURLException {

		Optional<Product> product = productS.findById(idProduct);
		if (product.isPresent()) {
			if (product.get().getImageFile() != null) {
				Resource file = new InputStreamResource(product.get().getImageFile().getBinaryStream());

				return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
						.contentLength(product.get().getImageFile().length()).body(file);
				
			}/* else if(product.get().getImageURL() != null) {
				Path imagePath = Application.FILES_FOLDER.resolve(Application.PRODUCTS_FOLDER);
				Resource image = new UrlResource(imagePath.toUri());
				return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
						.body(image);
			} */
		}
		return ResponseEntity.notFound().build();
	}
}

