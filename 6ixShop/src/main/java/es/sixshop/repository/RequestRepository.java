package es.sixshop.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import es.sixshop.model.Request;
import es.sixshop.model.User;

public interface RequestRepository extends JpaRepository<Request, Long>{
	Collection<Request> findByBuyerUserAndStatus(User user, String status);
}