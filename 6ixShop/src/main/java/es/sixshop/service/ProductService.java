package es.sixshop.service;

import java.sql.Date;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.sixshop.model.Product;
import es.sixshop.model.User;
import es.sixshop.repository.ProductRepository;

@Service
public class ProductService {
	private int SIZE_PAGE = 10;
	
	@Autowired
	private ProductRepository productR;
	
	public Optional<Product> findById(long id){
		return productR.findById(id);
	}
	
	public Collection<Product> findAll(){
		return productR.findAll();
	}
	
	public void save(Product product) {
		productR.save(product);
	}
	
	public void update(Product product) {
		productR.save(product);
	}
	
	public void delete(long id) {
		productR.deleteById(id);
	}
	
	public Page<Product> findBycategory(String category, Pageable pageable){
		return productR.findBycategory(category, PageRequest.of(pageable.getPageNumber(),SIZE_PAGE));
	}
	
	public Collection<Product> findByUser(User user){
		return productR.findByUser(user);
	}
	
	public Page<Product> findAll(Pageable pageable) {
		return productR.findAll(PageRequest.of(pageable.getPageNumber(),SIZE_PAGE));
	} 
	
	public Page<Product> findByRating(Pageable pageable){
		return productR.findByRating(PageRequest.of(pageable.getPageNumber(),SIZE_PAGE));
	}
	
	public Product findByIdProduct(Long idProduct){
		return productR.findByIdProduct(idProduct);
	}
	
	public Integer findByMonthSales(Date month1, Date month2, long idUser) {
		return productR.findByMonthSales(month1, month2,idUser);
	}
	
	public Integer findBySoldProducts(long idUser) {
		return productR.findBySoldProducts(idUser);
	}
}
