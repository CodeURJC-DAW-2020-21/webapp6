package es.sixshop.apirest.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.sixshop.apirest.detail.ProductAPIDetail;
import es.sixshop.apirest.detail.RequestAPIDetail;
import es.sixshop.apirest.detail.RequestDetailAPIDetail;
import es.sixshop.model.Product;
import es.sixshop.model.Request;
import es.sixshop.model.RequestDetail;
import es.sixshop.model.User;
import es.sixshop.service.ProductService;
import es.sixshop.service.RequestDetailService;
import es.sixshop.service.RequestService;
import es.sixshop.service.UserService;

@RestController
public class ProfileRestController {
	
	@Autowired
	private UserService userS;
	
	@Autowired
	private ProductService productS;
	
	@Autowired
	private RequestService requestS;
	
	@Autowired
	private RequestDetailService requestDetailS;
	
	@PostMapping("/api/products/") //NEW PRODUCT
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		productS.save(product);
		
		URI location = fromCurrentRequest().path("/{idProduct}").buildAndExpand(product.getIdProduct()).toUri();
		
		return ResponseEntity.created(location).body(product);
	}
	
	//@JsonView(ProductAPIDetail.class)
	@GetMapping("/api/profile/sales") //ALL SOLD PRODUCTS
	public Collection<Integer> getSoldProducts(){
		return null;
	}
	
	@JsonView(RequestAPIDetail.class)
	@GetMapping("/api/profile/shopping") //ALL BOUGHT PRODUCTS
	public Collection<Request> getBoughtroducts(HttpServletRequest request){
		int totalPrice = 0;
		
		String nickname = request.getUserPrincipal().getName();
        User user = userS.findByNickname(nickname).orElseThrow();
		
		Collection<Request> requests = requestS.findByBuyerUserAndStatusPaid(user);
		for (Request objRequest : requests) { // The total sum of each order is obtained
			totalPrice = 0;
			for (RequestDetail objRequestDetail : objRequest.getlRequestDetail()) {
				totalPrice+=objRequestDetail.getProductPrice();
			}
			objRequest.setTotalPrice(totalPrice);
		}
		
		return requests;
	}

	@JsonView(RequestAPIDetail.class) //ALL REQUEST
	@GetMapping("/api/requests/")
	public Collection<Request> getRequest(){
		return requestS.findAll();
	}
	
	@JsonView(RequestDetailAPIDetail.class) //ALL REQUEST DETAIL
	@GetMapping("/api/requestdetails/")
	public Collection<RequestDetail> getRequestDetail(){
		return requestDetailS.findAll();
	}
	
	
}
