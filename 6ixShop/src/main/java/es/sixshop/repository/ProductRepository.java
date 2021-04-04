package es.sixshop.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.sixshop.model.Product;
import es.sixshop.model.User;

public interface ProductRepository extends JpaRepository<Product, Long>{
	Collection<Product> findByUser(User user);
	Page<Product> findBycategory(String category, Pageable pageable);
	Page<Product> findAll(Pageable pageable);
	Product findByIdProduct(Long idProduct);
	
	@Query("SELECT PR FROM Product PR WHERE PR.rating>=0 ORDER BY PR.rating DESC")
	Page<Product> findByRating(Pageable pageable);
}
