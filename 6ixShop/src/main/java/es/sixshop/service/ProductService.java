package es.sixshop.service;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sixshop.model.Product;
import es.sixshop.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productR;
	
	@PostConstruct //Se ejecuta después de haber inyectado las dependencias
	public void init() {
		
		save(new Product("Shameless","Description of Shameless",30));
		save(new Product("Prueba2","asdasd1",30));
		save(new Product("Prueba3","asdasd2",50));
	}
	
	public Collection<Product> findAll(){
		return productR.findAll();
	}
	
	public void save(Product product) {
		productR.save(product);
	}
}
