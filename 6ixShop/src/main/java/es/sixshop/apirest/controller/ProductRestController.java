package es.sixshop.apirest.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.sixshop.apirest.detail.ProductDetail;
import es.sixshop.model.Product;
import es.sixshop.service.ProductService;

@RestController
public class ProductRestController {
	
	@Autowired
	private ProductService productS;
	
	@JsonView(ProductDetail.class)
	@GetMapping("/api/products/")
	public Collection<Product> getProducts(){
		return productS.findAll();
	}
	
	@GetMapping("/api/products/{idProduct}")
	public Product getProduct(@PathVariable long idProduct) {
		return productS.findByIdProduct(idProduct);
	}
}
