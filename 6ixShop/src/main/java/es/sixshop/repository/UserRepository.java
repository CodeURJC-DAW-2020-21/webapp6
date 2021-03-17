package es.sixshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.sixshop.model.Product;
import es.sixshop.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByNickname(String name);	
}
