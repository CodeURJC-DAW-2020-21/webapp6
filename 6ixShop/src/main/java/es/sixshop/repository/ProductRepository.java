package es.sixshop.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.sixshop.model.Product;
import es.sixshop.model.User;

public interface ProductRepository extends JpaRepository<Product, Long>{
	Collection<Product> findByUser(User user);
	Collection<Product> findBycategory(String category);
}
