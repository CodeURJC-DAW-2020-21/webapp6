package es.sixshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.sixshop.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	Optional<Product> findByidUser(long idUser);
}
