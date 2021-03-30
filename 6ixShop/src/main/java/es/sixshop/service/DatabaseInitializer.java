package es.sixshop.service;

import java.io.IOException;

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
import es.sixshop.repository.ProductRepository;
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
	private ProductRepository productR;
	
	@PostConstruct //Se ejecuta después de haber inyectado las dependencias
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
		
		/* Initializer Request and RequestDetail */
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
		Product pr2 = new Product("Pablo Simeone","Description of Pablo Simeone","Custom",30,user1); //Alberto Pacho
		setProductImage(pr2,"/static/img/imagenes/product/simeone.jpg");
		Product pr3 = new Product("True Detective","Description of True Detective","TVSeries",40,user2); //Javier Espin
		setProductImage(pr3,"/static/img/imagenes/product/trueDetective.jpg");
		Product pr4 = new Product("Gran Torino","Description of Gran Torino","Movies",40,user3); //Celia Sanjuan
		setProductImage(pr4,"/static/img/imagenes/product/granTorino.jpg");
		Product pr5 = new Product("Steve Jobs","Description of Steve Jobs","Custom",40,user3); //Sergio Martin
		setProductImage(pr5,"/static/img/imagenes/product/steveJobs.jpg");
		
		productR.save(pr1);
		productR.save(pr2);
		productR.save(pr3);
		productR.save(pr4);
		productR.save(pr5);
		
		/* Initializer RequestDetails */
		RequestDetail requestDetail1 = new RequestDetail(request1,pr3,pr3.getPrice());
		request1.setRequestDetail(requestDetail1);
		requestDetail1.setRequest(request1);
		
		requestDetailR.save(requestDetail1);
		requestR.save(request0);
	}
	
	private void setProductImage(Product product, String classpathResource) throws IOException {
		product.setImage(true);
		Resource image = new ClassPathResource(classpathResource);
		product.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
	}
}
