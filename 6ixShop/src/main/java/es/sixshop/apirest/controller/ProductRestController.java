package es.sixshop.apirest.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.sixshop.service.ImageService;
import es.sixshop.Application;
import es.sixshop.apirest.detail.ProductAPIDetail;
import es.sixshop.apirest.detail.ProductOwnerAPIDetail;
import es.sixshop.model.Product;
import es.sixshop.model.User;
import es.sixshop.service.ProductService;
import es.sixshop.service.UserService;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
	
	@Autowired
	private UserService userS;
	
	@Autowired
	private ProductService productS;
	
	@Autowired
	private ImageService imgService;
	
	@JsonView(ProductAPIDetail.class)
	@GetMapping("/") //ALL PRODUCTS
	public ResponseEntity<Map<String,Object>> getProducts(@RequestParam(defaultValue=Application.DEFAULT_PAGE) int page){
		Page<Product> productsPage = productS.findAll(PageRequest.of(page, Application.SIZE_PAGE));
		
		Map<String,Object> response = new HashMap<>();
		response.put("TOTAL ITEMS", productsPage.getTotalElements());
		response.put("CURRENT PAGE", productsPage.getNumber());
		response.put("TOTAL PAGE", productsPage.getTotalPages());
		response.put("products", productsPage.getContent());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@JsonView(ProductAPIDetail.class)
	@GetMapping("/rating") //ALL PRODUCTS RATING
	public ResponseEntity<Map<String,Object>> getProductsRating(@RequestParam(defaultValue=Application.DEFAULT_PAGE) int page){
		Page<Product> productsPage = productS.findByRating(PageRequest.of(page, Application.SIZE_PAGE));
		
		Map<String,Object> response = new HashMap<>();
		response.put("TOTAL ITEMS", productsPage.getTotalElements());
		response.put("CURRENT PAGE", productsPage.getNumber());
		response.put("TOTAL PAGE", productsPage.getTotalPages());
		response.put("products", productsPage.getContent());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@JsonView(ProductAPIDetail.class)
	@GetMapping("/{idProduct}") //SINGLE PRODUCT
	public ResponseEntity<Product> getProduct(@PathVariable long idProduct) {
		Product product = productS.findByIdProduct(idProduct);
		
		if(product!=null) {
			return ResponseEntity.ok(product);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@JsonView(ProductOwnerAPIDetail.class)
	@PutMapping("/{idProduct}") //EDIT PRODUCT
	public ResponseEntity<Product> replaceProduct(HttpServletRequest request, @PathVariable long idProduct, @RequestBody Product newProduct){
		String nickname = request.getUserPrincipal().getName();
        User user = userS.findByNickname(nickname).orElseThrow();
        
        Product product = productS.findByIdProduct(idProduct);
        
        if (product!=null) {
        	if(user.getIdUser()==product.getUser().getIdUser()) {
        		newProduct.setIdProduct(idProduct);
    			productS.save(newProduct);
    			return ResponseEntity.ok(product);
            } else 
            	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
        return ResponseEntity.notFound().build();
	}
	
	@JsonView(ProductOwnerAPIDetail.class)
	@DeleteMapping("/{idProduct}") //DELETE PRODUCT
	public ResponseEntity<Product> deleteProduct(HttpServletRequest request, @PathVariable long idProduct) throws IOException{
		String nickname = request.getUserPrincipal().getName();
        User user = userS.findByNickname(nickname).orElseThrow();
		
		Product product = productS.findByIdProduct(idProduct);
		
		if (product!=null) {
        	if(user.getIdUser()==product.getUser().getIdUser()) {
        		productS.delete(idProduct);
        		
        		if(product.getImageURL() != null) {
    				this.imgService.deleteImage(Application.PRODUCTS_FOLDER, idProduct);
    			}
        		
    			return ResponseEntity.ok(product);
            } else 
            	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
		
		return ResponseEntity.notFound().build();
	}
}
