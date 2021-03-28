package es.sixshop.service;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import es.sixshop.model.Product;
import es.sixshop.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productR;
	
	@PostConstruct //Se ejecuta después de haber inyectado las dependencias
	public void init() throws IOException {
		Product pr1 = new Product("Shameless","Description of Shameless","TVSeries",50,6);
		setProductImage(pr1,"/static/img/imagenes/product/shameless.jpg");
		productR.save(pr1);
		
		Product pr2 = new Product("Pablo Simeone","Description of Pablo Simeone","Custom",30,6);
		setProductImage(pr2,"/static/img/imagenes/product/simeone.jpg");
		productR.save(pr2);
		
		Product pr3 = new Product("True Detective","Description of True Detective","TVSeries",40,7);
		setProductImage(pr3,"/static/img/imagenes/product/trueDetective.jpg");
		productR.save(pr3);
		
		Product pr4 = new Product("Gran Torino","Description of Gran Torino","Movies",40,8);
		setProductImage(pr4,"/static/img/imagenes/product/granTorino.jpg");
		productR.save(pr4);
	}
	
	public Collection<Product> findAll(){
		return productR.findAll();
	}
	
	public Optional<Product> findById(long id){
		return productR.findById(id);
	}
	
	public void save(Product product) {
		productR.save(product);
	}
	
	public Collection<Product> findBycategory(String category){
		return productR.findBycategory(category);
	}
	
	private void setProductImage(Product product, String classpathResource) throws IOException {
		product.setImage(true);
		Resource image = new ClassPathResource(classpathResource);
		product.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
	}
}
