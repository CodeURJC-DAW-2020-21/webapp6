package es.sixshop.apirest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import com.fasterxml.jackson.annotation.JsonView;

import es.sixshop.Application;
import es.sixshop.apirest.detail.ProductAPIDetail;
import es.sixshop.model.Product;
import es.sixshop.service.ProductService;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {
	@Autowired
	private ProductService productS;
	
	
	@Operation(summary = "Get products by category")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Found the Product by category", 
					content = {@Content(
							mediaType = "application/json"
							)}
					),
			@ApiResponse(
					responseCode = "404", 
					description = "Category not found", 
					content = @Content
					) 
	})
	@JsonView(ProductAPIDetail.class)
	@GetMapping("/{category}") //GET CATEGORY
	public ResponseEntity<Map<String,Object>> getProductsCategory(@RequestParam(defaultValue=Application.DEFAULT_PAGE) int page, @PathVariable String category){
		Page<Product> productsCategory = productS.findBycategory(category, PageRequest.of(page, Application.SIZE_PAGE));
		
		Map<String,Object> response = new HashMap<>();
		response.put("TOTAL ITEMS", productsCategory.getTotalElements());
		response.put("CURRENT PAGE", productsCategory.getNumber());
		response.put("TOTAL PAGE", productsCategory.getTotalPages());
		response.put("products", productsCategory.getContent());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@Operation(summary = "Get a all categories")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Found category", 
					content = {@Content(
							mediaType = "application/json"
							)}
					) 
	})
	
	@JsonView(ProductAPIDetail.class)
	@GetMapping("/") //GET CATEGORY (PARAMETERS)
	public ResponseEntity<Map<String,Object>> getProductsCategoryParameters(@RequestParam(defaultValue=Application.DEFAULT_PAGE) int page, @RequestParam String category){
		Page<Product> productsCategory = productS.findBycategory(category, PageRequest.of(page, Application.SIZE_PAGE));
		
		Map<String,Object> response = new HashMap<>();
		response.put("TOTAL ITEMS", productsCategory.getTotalElements());
		response.put("CURRENT PAGE", productsCategory.getNumber());
		response.put("TOTAL PAGE", productsCategory.getTotalPages());
		response.put("products", productsCategory.getContent());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
