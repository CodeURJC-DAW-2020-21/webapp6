package es.sixshop.apirest.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import com.fasterxml.jackson.annotation.JsonView;

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
@RequestMapping("/api/carts")
public class CartRestController {
	
	@Autowired
	private UserService userS;
	
	@Autowired
	private ProductService productS;
	
	@Autowired
	private RequestDetailService requestDetailS;
	
	@Autowired
	private RequestService requestS;
	
	
	@Operation(summary = "Get a all products in cart")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Found products in cart", 
					content = {@Content(
							mediaType = "application/json"
							)}
					) 
	})
	@JsonView(ProductAPIDetail.class)
	@GetMapping("/") //ALL PRODUCTS CART
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
	
	@Operation(summary = "Add Product in Cart")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "201", 
					description = "Successful add product in cart", 
					content = {@Content(
							mediaType = "application/json"
							)}
					),
			@ApiResponse(
					responseCode = "406", 
					description = "Not Acceptable product exists", 
					content = @Content
					),
			@ApiResponse(
					responseCode = "404", 
					description = "Not Found Product or User", 
					content = @Content
					) 
	})
	
	@JsonView(ProductAPIDetail.class)
	@PostMapping("/{idProduct}") //ADD PRODUCT CART
	public ResponseEntity<Product> addProductCart(HttpServletRequest request, @PathVariable long idProduct){
		String nickname = request.getUserPrincipal().getName();
        User user = userS.findByNickname(nickname).orElseThrow();
        
        Product product = productS.findById(idProduct).orElseThrow();
        if(product!=null) {
        	if (user.getIdUser()!=product.getUser().getIdUser()) {
		        Request objRequest = requestS.findByBuyerUserAndStatus(user, "Cart");
		        RequestDetail requestDetail = new RequestDetail(objRequest,product,product.getPrice());
		        objRequest.setRequestDetail(requestDetail);
		        
		        requestDetailS.save(requestDetail);
		        requestS.save(objRequest);
				
		        return ResponseEntity.ok(product);
        	} else 
        		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	
        return ResponseEntity.notFound().build();
	}
	
	
	@Operation(summary = " Create Paid Cart")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Successful buys creation", 
					content = {@Content(
							mediaType = "application/json"
							)}
					),
			@ApiResponse(
					responseCode = "406", 
					description = "Not Acceptable buys", 
					content = @Content
					)
	})
	
	@PostMapping("/cardPayment/{idRequest}") //PAID CART
	public ResponseEntity<Request> requestCompleted(HttpServletRequest request, @PathVariable Long idRequest) {
		String nickname = request.getUserPrincipal().getName();
        User user = userS.findByNickname(nickname).orElseThrow();
        
        if(idRequest==requestS.findByIdRequestAndStatusCart(user)) {
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
