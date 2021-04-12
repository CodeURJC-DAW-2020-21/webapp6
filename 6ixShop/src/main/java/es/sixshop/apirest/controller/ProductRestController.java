package es.sixshop.apirest.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.sixshop.Application;
import es.sixshop.apirest.detail.ProductAPIDetail;
import es.sixshop.model.Product;
import es.sixshop.service.ProductService;

@RestController
public class ProductRestController {
	
	@Autowired
	private ProductService productS;
	
	@JsonView(ProductAPIDetail.class)
	@GetMapping("/api/products/") //ALL PRODUCTS
	public Collection<Product> getProducts(@RequestParam(defaultValue=Application.DEFAULT_PAGE) int page){
		Page<Product> productsPage = productS.findAll(PageRequest.of(page, Application.SIZE_PAGE));
		
		return productsPage.getContent();
	}
	
	@JsonView(ProductAPIDetail.class)
	@GetMapping("/api/productsRating/") //ALL PRODUCTS RATING
	public Collection<Product> getProductsRating(@RequestParam(defaultValue=Application.DEFAULT_PAGE) int page){
		Page<Product> productsPage = productS.findByRating(PageRequest.of(page, Application.SIZE_PAGE));
		
		return productsPage.getContent();
	}
	
	@JsonView(ProductAPIDetail.class)
	@GetMapping("/api/products/{idProduct}") //SINGLE PRODUCT
	public ResponseEntity<Product> getProduct(@PathVariable long idProduct) {
		Product product = productS.findByIdProduct(idProduct);
		
		if(product!=null) {
			return ResponseEntity.ok(product);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/api/products/{idProduct}") //EDIT PRODUCT
	public ResponseEntity<Product> replaceProduct(@PathVariable long idProduct, @RequestBody Product newProduct){
		// ************** COMPROBAR USUARIO **************
		
		Product product = productS.findByIdProduct(idProduct);
		
		if(product!=null) {
			newProduct.setIdProduct(idProduct);
			productS.save(newProduct);
			return ResponseEntity.ok(product);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/api/products/{idProduct}") //DELETE PRODUCT
	public ResponseEntity<Product> deleteProduct(@PathVariable long idProduct){
		// ************** COMPROBAR USUARIO **************
		
		Product product = productS.findByIdProduct(idProduct);
		
		if (product!=null) {
			productS.delete(idProduct);
			return ResponseEntity.ok(product);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
