package es.sixshop.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import es.sixshop.service.EmailService;
import es.sixshop.service.ProductService;
import es.sixshop.service.RequestDetailService;
import es.sixshop.service.RequestService;
import es.sixshop.service.UserService;
import es.sixshop.model.Product;
import es.sixshop.model.Request;
import es.sixshop.model.RequestDetail;
import es.sixshop.model.User;
import es.sixshop.repository.UserRepository;

@Controller
public class ProfileController {	
	@Autowired
	private UserService userS;
	
	@Autowired
	private ProductService productS;
	
	@Autowired
	private RequestService requestS;
	
	@Autowired
	private EmailService emailS;
	
	@Autowired
	private RequestDetailService requestDetailS;
	
	@GetMapping("/profile")
	public String showProfile(Model model, HttpServletRequest request) {
		int totalPrice = 0;
		Date month1,month2;
		int soldProducts = 0;
		
		//User data
		String nickname = request.getUserPrincipal().getName();
		User user = userS.findByNickname(nickname).orElseThrow();
		
		model.addAttribute("user",user);
		model.addAttribute("nickname",user.getNickname());
		model.addAttribute("mail",user.getMail());
		model.addAttribute("profile",true);
		
		// Number of products sold
		soldProducts = productS.findBySoldProducts(user.getIdUser());
		model.addAttribute("soldProducts",soldProducts);
		
		// Product data uploaded
		Collection<Product> products = productS.findByUserAndVisible(user);
		model.addAttribute("products",products);
		
		// Data of products sold
		List<Integer> lSales = new ArrayList<Integer>();
		for(int x=1;x<=12;x++) {
			LocalDate initial = LocalDate.of(2021, x, 1);
			month1 = Date.valueOf(initial.withDayOfMonth(x));
			month2 = Date.valueOf(initial.withDayOfMonth(initial.lengthOfMonth()));
			lSales.add(productS.findByMonthSales(month1, month2, user.getIdUser()));
		}
		model.addAttribute("January",lSales.get(0));
		model.addAttribute("February",lSales.get(1));
		model.addAttribute("March",lSales.get(2));
		model.addAttribute("April",lSales.get(3));
		model.addAttribute("May",lSales.get(4));
		model.addAttribute("June",lSales.get(5));
		model.addAttribute("July",lSales.get(6));
		model.addAttribute("August",lSales.get(7));
		model.addAttribute("September",lSales.get(8));
		model.addAttribute("October",lSales.get(9));
		model.addAttribute("November",lSales.get(10));
		model.addAttribute("December",lSales.get(11));
		
		// Purchased order data
		Collection<Request> requests = requestS.findByBuyerUserAndStatusPaid(user);
		for (Request objRequest : requests) { // The total sum of each order is obtained
			totalPrice = 0;
			for (RequestDetail objRequestDetail : objRequest.getlRequestDetail()) {
				totalPrice+=objRequestDetail.getProductPrice();
			}
			objRequest.setTotalPrice(totalPrice);
		}
		model.addAttribute("requests",requests);
		
		return "profile";
	}
	
	@PostMapping("/profile")
    public String newProduct(HttpServletRequest request, Model model, Product product, MultipartFile imageField) throws IOException {
		String nickname = request.getUserPrincipal().getName();
        User user = userS.findByNickname(nickname).orElseThrow();
    	
		if (!imageField.isEmpty()) {
			product.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			product.setImage(true);
		}

		product.setUser(user);
		product.setRating(-1);
		product.setVisible(true);
		productS.save(product);
		
		model.addAttribute("product", product.getIdProduct());
		
		emailS.sendEmail("sixshop.sixshop@gmail.com", "¡Has subido un producto con éxito!", "Creaste el producto "+product.getProductName()+" por " +product.getPrice()+ " $");
		
		return "redirect:/profile";
    }
	
	@GetMapping("/profile/rate/{idRequestDetail}/{idProduct}/{rating}")
	public String rateProduct(Model model, HttpServletRequest request, @PathVariable long idRequestDetail, @PathVariable long idProduct, @PathVariable int rating) {
		//User data
		String nickname = request.getUserPrincipal().getName();
		User user = userS.findByNickname(nickname).orElseThrow();
		
		model.addAttribute("user",user);
		model.addAttribute("nickname",user.getNickname());
		model.addAttribute("mail",user.getMail());
		model.addAttribute("profile",true);
		
		Collection<Request> requests = requestS.findByBuyerUserAndStatusPaid(user);
		RequestDetail requestDetail = requestDetailS.findById(idRequestDetail).orElseThrow();

		for (Request objRequest : requests) {
			if(objRequest.getIdRequest() == requestDetail.getRequest().getIdRequest()) {
				for(RequestDetail objRequestDetail: objRequest.getlRequestDetail()) {
					if(objRequestDetail.getProduct().getIdProduct() == idProduct) {
						objRequestDetail.setRating(rating);
						requestDetailS.recalculateProductRating(objRequestDetail.getProduct().getIdProduct(), rating);
						requestDetailS.save(objRequestDetail);
					}
				}
			}
		}
		
		return "redirect:/profile";
		
	}
}
