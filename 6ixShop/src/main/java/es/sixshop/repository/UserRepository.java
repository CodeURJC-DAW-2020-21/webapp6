package es.sixshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.sixshop.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
