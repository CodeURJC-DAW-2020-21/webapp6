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
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
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
	
	@JsonView(UserOwnerAPIDetail.class)
	@GetMapping("/") //USER DATA AND PRODUCTS PROFILE
	public User getUserData(HttpServletRequest request){
		String nickname = request.getUserPrincipal().getName();
		User user = userS.findByNickname(nickname).orElseThrow();
		
		return user;
	}
	
	@JsonView(ProductOwnerAPIDetail.class)
	@PostMapping("/products/") //NEW PRODUCT
	public ResponseEntity<Product> createProduct(HttpServletRequest request, @RequestBody Product product){
		String nickname = request.getUserPrincipal().getName();
        User user = userS.findByNickname(nickname).orElseThrow();
		
        product.setUser(user);
		productS.save(product);
		
		URI location = fromCurrentRequest().path("/{idProduct}").buildAndExpand(product.getIdProduct()).toUri();
		
		emailS.sendEmail("sixshop.sixshop@gmail.com", "¡Has subido un producto con éxito!", "Creaste el producto "+product.getProductName()+" por " +product.getPrice()+ " $");
		
		return ResponseEntity.created(location).body(product);
	}
	
	@GetMapping("/{idProduct}/image") //DOWNLOAD IMAGE
	public ResponseEntity<Object> downloadImage(@PathVariable long idProduct) throws MalformedURLException {

		return this.imgService.createResponseFromImage(Application.PRODUCTS_FOLDER, idProduct);
	}
	
	@JsonView(ProductOwnerAPIDetail.class)
	@PostMapping("/{idProduct}/image") //UPLOAD IMAGE
	public ResponseEntity<Object> uploadImage(HttpServletRequest request, @PathVariable long idProduct, @RequestParam MultipartFile imageFile)
			throws IOException {
		
		String nickname = request.getUserPrincipal().getName();
        User user = userS.findByNickname(nickname).orElseThrow();
		
		Product product = productS.findByIdProduct(idProduct);

		if (product!=null && user.getIdUser()==product.getUser().getIdUser()) {

			URI location = fromCurrentRequest().build().toUri();
			
			imgService.saveImage(Application.PRODUCTS_FOLDER, product.getIdProduct(), imageFile);
			
			if (!imageFile.isEmpty()) {
				product.setImageFile(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
				product.setImage(true);
			}
			
			product.setImageURL(location.toString());
			productS.save(product);


			return ResponseEntity.created(location).body(product);

		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{idProduct}/image") //DELETE IMAGE
	public ResponseEntity<Object> deleteImage(HttpServletRequest request, @PathVariable long idProduct) throws IOException {
		
		String nickname = request.getUserPrincipal().getName();
        User user = userS.findByNickname(nickname).orElseThrow();

		Product product = productS.findByIdProduct(idProduct);
		
		if (product!=null && user.getIdUser()==product.getUser().getIdUser()) {
			
			product.setImageURL(null);
			productS.save(product);
			
			this.imgService.deleteImage(Application.PRODUCTS_FOLDER, idProduct);
			
			return ResponseEntity.noContent().build();
			
		} else {
			return ResponseEntity.notFound().build();
		}		
	}
	
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
	
	private void setProductImage(Product product, String classpathResource) throws IOException {
		product.setImage(true);
		Resource image = new ClassPathResource(classpathResource);
		product.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
	}
}
