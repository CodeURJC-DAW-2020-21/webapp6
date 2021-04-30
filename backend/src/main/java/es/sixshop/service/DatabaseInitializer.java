package es.sixshop.service;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;

import javax.annotation.PostConstruct;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.sixshop.model.Request;
import es.sixshop.model.RequestDetail;
import es.sixshop.model.Product;
import es.sixshop.model.User;
import es.sixshop.repository.RequestRepository;
import es.sixshop.repository.RequestDetailRepository;
import es.sixshop.repository.UserRepository;

@Service
public class DatabaseInitializer {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userR;
	
	@Autowired
	private RequestRepository requestR;
	
	@Autowired
	private RequestDetailRepository requestDetailR;
	
	@Autowired
	private RequestDetailService requestDetailS;
	
	@Autowired
	private ProductService productS;
	
	@PostConstruct // Run after dependencies have been injected
	public void init() throws IOException {
		
		/* Initializer Users */
		User user0 = new User("SixShop","$2y$12$1GmosFZoh1ekJdUZ9ZOOQOkc18lrOzI9MbGJvZStt0dfBEPPXS5Na","admin@sixshop.es",666000666,"ADMIN");
		User user1 = new User("Alberto Pacho",passwordEncoder.encode("123"),"albertopacho@gmail.com",666111666,"USER");
		User user2 = new User("Javier Espin",passwordEncoder.encode("123"),"javierespin@gmail.com",666222666,"USER");
		User user3 = new User("Celia Sanjuan",passwordEncoder.encode("123"),"celiasanjuan@gmail.com",666333666,"USER");
		User user4 = new User("Sergio Martin",passwordEncoder.encode("123"),"sergiomartin@gmail.com",666444666,"USER");
		
		userR.save(user0);
		userR.save(user1);
		userR.save(user2);
		userR.save(user3);
		userR.save(user4);
		
		/* Initializer Request(Cart) and RequestDetail */
		Request request0 = new Request(user0);
		user0.setRequest(request0);		
		
		Request request1 = new Request(user1);
		user1.setRequest(request1);
		
		Request request2 = new Request(user2);
		user2.setRequest(request2);
		
		Request request3 = new Request(user3);
		user3.setRequest(request3);
		
		Request request4 = new Request(user4);
		user4.setRequest(request4);
		
		requestR.save(request0);
		requestR.save(request1);
		requestR.save(request2);
		requestR.save(request3);
		requestR.save(request4);
		
		/* Initializer Products */
		Product pr1 = new Product("Shameless","Description of Shameless","TVSeries",50,user1); //Alberto Pacho
		setProductImage(pr1,"/static/img/imagenes/product/shameless.jpg");
		Product pr2 = new Product("Pablo Simeone","Description of Pablo Simeone","Custom",30,user4); //Sergio Martin
		setProductImage(pr2,"/static/img/imagenes/product/simeone.jpg");
		Product pr3 = new Product("True Detective","Description of True Detective","TVSeries",40,user2); //Javier Espin
		setProductImage(pr3,"/static/img/imagenes/product/trueDetective.jpg");
		Product pr4 = new Product("Gran Torino","Description of Gran Torino","Movies",40,user3); //Celia Sanjuan
		setProductImage(pr4,"/static/img/imagenes/product/granTorino.jpg");
		Product pr5 = new Product("Steve Jobs","Description of Steve Jobs","Custom",20,user4); //Sergio Martin
		setProductImage(pr5,"/static/img/imagenes/product/steveJobs.jpg");
		Product pr6 = new Product("Mafalda","Description of Mafalda","Comics",35,user4); //Sergio Martin
		setProductImage(pr6,"/static/img/imagenes/product/mafalda.jpg");
		
		productS.save(pr1);
		productS.save(pr2);
		productS.save(pr3);
		productS.save(pr4);
		productS.save(pr5);
		productS.save(pr6);
		
		for(int i=17; i<50;i+=6) {
			Product pr7 = new Product("Shameless","Description of ProductN-"+i,"TVSeries",50,user1); //Alberto Pacho
			setProductImage(pr7,"/static/img/imagenes/product/shameless.jpg");
			Product pr8 = new Product("Pablo Simeone","Description of ProductN-"+(i+1),"Custom",30,user4); //Sergio Martin
			setProductImage(pr8,"/static/img/imagenes/product/simeone.jpg");
			Product pr9 = new Product("True Detective","Description of ProductN-"+(i+2),"TVSeries",40,user2); //Javier Espin
			setProductImage(pr9,"/static/img/imagenes/product/trueDetective.jpg");
			Product pr10 = new Product("Gran Torino","Description of ProductN-"+(i+3),"Movies",40,user3); //Celia Sanjuan
			setProductImage(pr10,"/static/img/imagenes/product/granTorino.jpg");
			Product pr11 = new Product("Steve Jobs","Description of ProductN-"+(i+4),"Custom",20,user4); //Sergio Martin
			setProductImage(pr11,"/static/img/imagenes/product/steveJobs.jpg");
			Product pr12 = new Product("Mafalda","Description of ProductN-"+(i+5),"Comics",35,user4); //Sergio Martin
			setProductImage(pr12,"/static/img/imagenes/product/mafalda.jpg");
			
			productS.save(pr7);
			productS.save(pr8);
			productS.save(pr9);
			productS.save(pr10);
			productS.save(pr11);
			productS.save(pr12);
		}
		
		/* Initializer RequestDetails */
		RequestDetail requestDetail1 = new RequestDetail(request1,pr3,pr3.getPrice());
		request1.setRequestDetail(requestDetail1);
		requestDetail1.setRequest(request1);
		
		requestDetailR.save(requestDetail1);
		requestR.save(request1);
		
		/* Initializer Request PAID (1) */
		Request request5 = new Request(user1,"PAID");
		user1.setRequest(request5);
		request5.setDate(Date.valueOf(LocalDate.of(2021, Month.JANUARY, 17)));
		requestR.save(request5);
		
		RequestDetail requestDetail2 = new RequestDetail(request5,pr4,pr4.getPrice());
		requestDetail2.setRating(5);
		requestDetailS.recalculateProductRating(pr4.getIdProduct(),5);
		request5.setRequestDetail(requestDetail2);
		requestDetail2.setRequest(request5);
		RequestDetail requestDetail3 = new RequestDetail(request5,pr5,pr5.getPrice());
		requestDetail3.setRating(4);
		requestDetailS.recalculateProductRating(pr5.getIdProduct(),4);
		request5.setRequestDetail(requestDetail3);
		requestDetail3.setRequest(request5);
		
		requestDetailR.save(requestDetail2);
		requestDetailR.save(requestDetail3);
		requestR.save(request5);
		
		/* Initializer Request PAID (2) */
		Request request6 = new Request(user1,"PAID");
		user1.setRequest(request6);
		request6.setDate(Date.valueOf(LocalDate.of(2021, Month.FEBRUARY, 13)));
		requestR.save(request6);
		
		RequestDetail requestDetail4 = new RequestDetail(request6,pr2,pr2.getPrice());
		request6.setRequestDetail(requestDetail4);
		requestDetail4.setRequest(request6);
		
		requestDetailR.save(requestDetail4);
		requestR.save(request6);
		
		/* Initializer Request PAID (3) */
		Request request7 = new Request(user1,"PAID");
		user1.setRequest(request7);
		request7.setDate(Date.valueOf(LocalDate.of(2021, Month.FEBRUARY, 14)));
		requestR.save(request7);
		
		RequestDetail requestDetail5 = new RequestDetail(request7,pr5,pr5.getPrice());
		requestDetail5.setRating(2);
		requestDetailS.recalculateProductRating(pr5.getIdProduct(),2);
		request7.setRequestDetail(requestDetail5);
		requestDetail5.setRequest(request7);
		RequestDetail requestDetail6 = new RequestDetail(request7,pr5,pr5.getPrice());
		requestDetail6.setRating(2);
		requestDetailS.recalculateProductRating(pr5.getIdProduct(),2);
		request7.setRequestDetail(requestDetail6);
		requestDetail6.setRequest(request7);
		
		requestDetailR.save(requestDetail5);
		requestDetailR.save(requestDetail6);
		requestR.save(request7);
	}
	
	private void setProductImage(Product product, String classpathResource) throws IOException {
		product.setImage(true);
		Resource image = new ClassPathResource(classpathResource);
		product.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
	}
}
