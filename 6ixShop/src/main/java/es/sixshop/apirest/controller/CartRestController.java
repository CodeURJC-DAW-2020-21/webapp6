package es.sixshop.apirest.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.sixshop.Application;
import es.sixshop.apirest.detail.ProductAPIDetail;
import es.sixshop.model.Product;
import es.sixshop.model.Request;
import es.sixshop.model.RequestDetail;
import es.sixshop.model.User;
import es.sixshop.service.ProductService;
import es.sixshop.service.RequestDetailService;
import es.sixshop.service.RequestService;
import es.sixshop.service.UserService;

@RestController
public class CartRestController {
	
	@Autowired
	private UserService userS;
	
	@Autowired
	private ProductService productS;
	
	@Autowired
	private RequestDetailService requestDetailS;
	
	@Autowired
	private RequestService requestS;
	
	@JsonView(ProductAPIDetail.class)
	@GetMapping("/api/carts/") //ALL PRODUCTS CART
	public ResponseEntity<Map<String,Object>> getCart(HttpServletRequest request){
		int totalPrice = 0;
		
		String nickname = request.getUserPrincipal().getName();
        User user = userS.findByNickname(nickname).orElseThrow();
		
        // Load the car
        Request requestUser = requestS.findByBuyerUserAndStatus(user,"Cart");
        Collection<Product> products = requestDetailS.findProductOfRequestDetail(requestUser);
        
        // The total price of the products is calculated
        for (Product product : products) {
        	totalPrice+= product.getPrice();
        }
        
        Map<String,Object> response = new HashMap<>();
        response.put("totalPrice", totalPrice);
        response.put("products", products);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@JsonView(ProductAPIDetail.class)
	@PostMapping("/api/carts/{idProduct}") //ADD PRODUCT CART
	public ResponseEntity<Product> addProductCart(HttpServletRequest request, @PathVariable long idProduct){
		String nickname = request.getUserPrincipal().getName();
        User user = userS.findByNickname(nickname).orElseThrow();
        
        Product prod = productS.findById(idProduct).orElseThrow();
        if(prod!=null) {
	        Request objRequest = requestS.findByBuyerUserAndStatus(user, "Cart");
	        RequestDetail requestDetail = new RequestDetail(objRequest,prod,prod.getPrice());
	        objRequest.setRequestDetail(requestDetail);
	        
	        requestDetailS.save(requestDetail);
	        requestS.save(objRequest);
			
	        return ResponseEntity.ok(prod);
        } else {
        	return ResponseEntity.notFound().build();
        }
	}
	
	@PostMapping("/api/carts/cardPayment/{idRequest}") //PAID CART
	public ResponseEntity<Request> requestCompleted(HttpServletRequest request, @PathVariable Long idRequest) {
		String nickname = request.getUserPrincipal().getName();
        User user = userS.findByNickname(nickname).orElseThrow();
        
        if(idRequest==requestS.findByIdRequestAndStatus(user)) {
	        /* The ORDER is saved as PAID and removed from the cart */
	        Request requestUser = requestS.findById(idRequest).orElseThrow();
	        requestUser.setStatus("PAID");
	        requestUser.setDate(Date.valueOf(LocalDate.now()));
	        
	        requestS.save(requestUser);
	        
	        /* Another ORDER is created as an empty CART */
	        Request newRequest = new Request(user);
	        requestS.save(newRequest);
	        
	        return ResponseEntity.ok(requestUser);
        } else  {
        	return ResponseEntity.notFound().build();
        }
	}

}
