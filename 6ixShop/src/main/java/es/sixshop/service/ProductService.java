package es.sixshop.service;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sixshop.model.Product;
import es.sixshop.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productR;
	
	@Autowired
	private ImageService imageService;
	
	@PostConstruct //Se ejecuta después de haber inyectado las dependencias
	public void init() {
		save(new Product("Shameless","Description of Shameless","Series",50,6));
		save(new Product("Pablo Simeone","Description of Pablo Simeone","Retrato",30,6));
		save(new Product("True Detective","Description of True Detective","Series",40,7));
		save(new Product("Gran Torino","Description of Gran Torino","Peliculas",40,8));
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
}
