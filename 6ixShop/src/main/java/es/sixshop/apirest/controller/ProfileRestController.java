package es.sixshop.apirest.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import com.fasterxml.jackson.annotation.JsonView;

import es.sixshop.service.ImageService;
import es.sixshop.Application;
import es.sixshop.apirest.detail.ProductOwnerAPIDetail;
import es.sixshop.apirest.detail.RequestAPIDetail;
import es.sixshop.apirest.detail.UserOwnerAPIDetail;
import es.sixshop.model.Product;
import es.sixshop.model.Request;
import es.sixshop.model.RequestDetail;
import es.sixshop.model.User;
import es.sixshop.service.EmailService;
import es.sixshop.service.ProductService;
import es.sixshop.service.RequestDetailService;
import es.sixshop.service.RequestService;
import es.sixshop.service.UserService;

@RestController
@RequestMapping("/api/profiles")
public class ProfileRestController {
	
	@Autowired
	private ImageService imgService;
	
	@Autowired
	private UserService userS;
	
	@Autowired
	private ProductService productS;
	
	@Autowired
	private RequestService requestS;
	
	@Autowired
	private RequestDetailService requestDetailS;
	
	@Autowired
	private EmailService emailS;
	
	@Operation(summary = "Get a all data profile")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Found all data profile ", 
					content = {@Content(
							mediaType = "application/json"
							)}
					) 
	})
	
	@JsonView(UserOwnerAPIDetail.class)
	@GetMapping("/") //USER DATA AND PRODUCTS PROFILE
	public User getUserData(HttpServletRequest request){
		String nickname = request.getUserPrincipal().getName();
		User user = userS.findByNickname(nickname).orElseThrow();
		
		return user;
	}
	
	@Operation(summary = "Create a Products")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "201", 
					description = "Successful Products creation", 
					content = {@Content(
							mediaType = "application/json"
							)}
					),
			@ApiResponse(
					responseCode = "406", 
					description = "Not Acceptable products parametrs  exists", 
					content = @Content
					) 
	})
	
	@JsonView(ProductOwnerAPIDetail.class)
	@PostMapping("/products") //NEW PRODUCT
	public ResponseEntity<Product> createProduct(HttpServletRequest request, @RequestBody Product product){
		String nickname = request.getUserPrincipal().getName();
        User user = userS.findByNickname(nickname).orElseThrow();
		
        product.setUser(user);
		productS.save(product);
		
		URI location = fromCurrentRequest().path("/{idProduct}").buildAndExpand(product.getIdProduct()).toUri();
		
		emailS.sendEmail("sixshop.sixshop@gmail.com", "¡Has subido un producto con éxito!", "Creaste el producto "+product.getProductName()+" por " +product.getPrice()+ " $");
		
		return ResponseEntity.created(location).body(product);
	}
	
	@Operation(summary = "Get a ImageProduct by its id")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Create the Image Product", 
					content = {@Content(
							mediaType = "application/json"
							)}
					),
			@ApiResponse(
					responseCode = "404", 
					description = "Product not found", 
					content = @Content
					),
			@ApiResponse(
					responseCode = "204", 
					description = "Image not found", 
					content = @Content
					)
	})
	
	@GetMapping("/{idProduct}/image") //DOWNLOAD IMAGE
	public ResponseEntity<Object> downloadImage(@PathVariable long idProduct) throws MalformedURLException {

		return this.imgService.createResponseFromImage(Application.PRODUCTS_FOLDER, idProduct);
	}
	
	@Operation(summary = "Create a Image Product by its id")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "201", 
					description = "Create the Image Product", 
					content = {@Content(
							mediaType = "application/json"
							)}
					),
			@ApiResponse(
					responseCode = "404", 
					description = "Product not found", 
					content = @Content
					),
			@ApiResponse(
					responseCode = "204", 
					description = "Image not found", 
					content = @Content
					)
	})
	
	@JsonView(ProductOwnerAPIDetail.class)
	@PostMapping("/{idProduct}/image") //UPLOAD IMAGE
	public ResponseEntity<Object> uploadImage(HttpServletRequest request, @PathVariable long idProduct, @RequestParam MultipartFile imageFile)
			throws IOException {
		
		String nickname = request.getUserPrincipal().getName();
        User user = userS.findByNickname(nickname).orElseThrow();
		
		Product product = productS.findByIdProduct(idProduct);

		if (product!=null) { 
			if (user.getIdUser()==product.getUser().getIdUser()) {

				URI location = fromCurrentRequest().build().toUri();
				
				imgService.saveImage(Application.PRODUCTS_FOLDER, product.getIdProduct(), imageFile);
				
				if (!imageFile.isEmpty()) {
					product.setImageFile(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
					product.setImage(true);
				}
				
				product.setImageURL(location.toString());
				productS.save(product);
	
	
				return ResponseEntity.created(location).body(product);
			} else 
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@Operation(summary = "Deleted image  Product")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "201", 
					description = "Successful image Product creation", 
					content = {@Content(
							mediaType = "application/json"
							)}
					),
			@ApiResponse(
					responseCode = "404", 
					description = "Product not found", 
					content = @Content
					) 
	})
	
	@DeleteMapping("/{idProduct}/image") //DELETE IMAGE
	public ResponseEntity<Object> deleteImage(HttpServletRequest request, @PathVariable long idProduct) throws IOException {
		
		String nickname = request.getUserPrincipal().getName();
        User user = userS.findByNickname(nickname).orElseThrow();

		Product product = productS.findByIdProduct(idProduct);
		
		if (product!=null) {
			if (user.getIdUser()==product.getUser().getIdUser()) {
			
				product.setImageURL(null);
				productS.save(product);
				
				this.imgService.deleteImage(Application.PRODUCTS_FOLDER, idProduct);
				
				return ResponseEntity.noContent().build();
			} else
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		return ResponseEntity.notFound().build();	
	}
	
	
	@Operation(summary = "Get a all sold Products")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Found sold Products", 
					content = {@Content(
							mediaType = "application/json"
							)}
					) 
	})
	
	@GetMapping("/sales") //ALL SOLD PRODUCTS (GRAPHIC)
	public ResponseEntity<Map<String,Integer>> getSoldProducts(HttpServletRequest request){
		Date month1,month2;
		
		String nickname = request.getUserPrincipal().getName();
        User user = userS.findByNickname(nickname).orElseThrow();
		
        Map<String,Integer> response = new HashMap<>();
		List<Integer> lSales = new ArrayList<Integer>();
		for(int x=1;x<=12;x++) {
			LocalDate initial = LocalDate.of(2021, x, 1);
			month1 = Date.valueOf(initial.withDayOfMonth(x));
			month2 = Date.valueOf(initial.withDayOfMonth(initial.lengthOfMonth()));
			lSales.add(productS.findByMonthSales(month1, month2, user.getIdUser()));
		}
		
		response.put("January", lSales.get(0));
		response.put("February", lSales.get(1));
		response.put("March", lSales.get(2));
		response.put("April", lSales.get(3));
		response.put("May", lSales.get(4));
		response.put("June", lSales.get(5));
		response.put("July", lSales.get(6));
		response.put("August", lSales.get(7));
		response.put("September", lSales.get(8));
		response.put("October", lSales.get(9));
		response.put("November", lSales.get(10));
		response.put("December", lSales.get(11));
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	@Operation(summary = "Get a all  bought Products")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Found all bought Products", 
					content = {@Content(
							mediaType = "application/json"
							)}
					) 
	})
	@JsonView(RequestAPIDetail.class)
	@GetMapping("/shopping") //ALL BOUGHT PRODUCTS
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
	
	@Operation(summary = "add raiting  Product")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "201", 
					description = "Successful rainting Product modification", 
					content = {@Content(
							mediaType = "application/json"
							)}
					),
			@ApiResponse(
					responseCode = "404", 
					description = "Product not found", 
					content = @Content
					),
			@ApiResponse(
					responseCode = "406", 
					description = "Not Acceptable  products parameters  exists", 
					content = @Content
					) 
	})
	@PutMapping("/{idRequestDetail}/{idProduct}/{rating}") //RATING PRODUCT
	public ResponseEntity<RequestDetail> ratingProduct(HttpServletRequest request,@PathVariable long idRequestDetail, @PathVariable long idProduct, @PathVariable int rating){
		String nickname = request.getUserPrincipal().getName();
        User user = userS.findByNickname(nickname).orElseThrow();
        
        Optional<RequestDetail> requestDetail = requestDetailS.findById(idRequestDetail);
                
        if(requestDetail.get().getRequest().getBuyerUser().getIdUser()==user.getIdUser()) { //User owns of ProductDetail
        	if(requestDetail.get().getProduct().getIdProduct()==idProduct) { //Product appertain of ProductDetail
        		if(rating>0 && rating<=5) {
        			requestDetail.get().setRating(rating);
        			requestDetailS.recalculateProductRating(idProduct, rating);
        			requestDetailS.save(requestDetail.get());   
        			return ResponseEntity.ok(requestDetail.get());
        		}
        	}
        }
        
        return ResponseEntity.notFound().build();
	}	
}
