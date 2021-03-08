package es.sixshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.sixshop.model.User;

public interface ProductRepository extends JpaRepository<User, Long>{

}
