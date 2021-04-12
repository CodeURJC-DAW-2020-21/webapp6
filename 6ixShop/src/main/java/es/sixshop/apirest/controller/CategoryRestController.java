package es.sixshop.apirest.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.sixshop.Application;
import es.sixshop.apirest.detail.ProductAPIDetail;
import es.sixshop.model.Product;
import es.sixshop.service.ProductService;
import es.sixshop.service.UserService;

@RestController
public class CategoryRestController {
	@Autowired
	private ProductService productS;
	
	@Autowired
	private UserService userS;
	
	@JsonView(ProductAPIDetail.class)
	@GetMapping("/api/categories/{category}") //GET CATEGORY
	public Collection<Product> getProductsCategory(@RequestParam(defaultValue=Application.DEFAULT_PAGE) int page, @PathVariable String category){
		Page<Product> productsCategory = productS.findBycategory(category, PageRequest.of(page, Application.SIZE_PAGE));
		
		return productsCategory.getContent();
	}
}
