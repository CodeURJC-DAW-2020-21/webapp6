package es.sixshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.sixshop.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
}
