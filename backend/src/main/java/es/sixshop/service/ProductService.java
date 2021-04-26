package es.sixshop.service;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.sixshop.Application;
import es.sixshop.model.Product;
import es.sixshop.model.Request;
import es.sixshop.model.RequestDetail;
import es.sixshop.model.User;
import es.sixshop.repository.ProductRepository;

@Service
public class ProductService {	
	@Autowired
	private ProductRepository productR;
	
	@Autowired
	private RequestService requestS;
	
	@Autowired
	private RequestDetailService requestDetailS;
	
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
	
	/*
	public void delete(Product product) {
		productR.delete(product);
	}*/
	
	public void hideProduct(long idProduct) {
		//productR.deleteById(id);
		Product product = productR.findByIdProduct(idProduct);
		product.setVisible(false);
		productR.save(product);
		Collection<Request> requests = requestS.findByStatusCart();
		for (Request request : requests) {
			List<RequestDetail> requestDetails = request.getlRequestDetail();
			for (RequestDetail requestDetail : requestDetails) {
				if (requestDetail.getProduct().getIdProduct()==product.getIdProduct()) {
					requestDetailS.delete(requestDetail.getIdRequestDetail());
					requestS.save(request);
				}
			}
		}
	}
	
	public Page<Product> findByCategoryAndVisible(String category, Pageable pageable){
		return productR.findByCategoryAndVisible(category, PageRequest.of(pageable.getPageNumber(),Application.SIZE_PAGE),true);
	}
	
	public Collection<Product> findByUserAndVisible(User user){
		return productR.findByUserAndVisible(user, true);
	}
	
	public Page<Product> findByVisible(Pageable pageable) {
		return productR.findByVisible(PageRequest.of(pageable.getPageNumber(),Application.SIZE_PAGE),true);
	} 
	
	public Page<Product> findByRatingAndVisible(Pageable pageable){
		return productR.findByRatingAndVisible(PageRequest.of(pageable.getPageNumber(),Application.SIZE_PAGE));
	}
	
	public Product findByIdProductAndVisible(Long idProduct){
		return productR.findByIdProductAndVisible(idProduct,true);
	}
	
	public Integer findByMonthSales(Date month1, Date month2, long idUser) {
		return productR.findByMonthSales(month1, month2,idUser);
	}
	
	public Integer findBySoldProducts(long idUser) {
		return productR.findBySoldProducts(idUser);
	}
}
