package es.sixshop.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.sixshop.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	Collection<Product> findByidUser(long idUser);
	Collection<Product> findBycategory(String category);
}
