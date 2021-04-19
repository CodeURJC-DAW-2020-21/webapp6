package es.sixshop.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.sixshop.model.Request;
import es.sixshop.model.User;

public interface RequestRepository extends JpaRepository<Request, Long>{
	Collection<Request> findByBuyerUserAndStatus(User user, String status);
	long findByIdRequestAndStatus(User user, String status);
	
	@Query("SELECT REQ FROM Request REQ WHERE REQ.status='Cart'")
	Collection<Request> findByStatusCart();
	
	@Query("SELECT REQ.idRequest FROM Request REQ WHERE REQ.buyerUser=?1 AND REQ.status='Cart'")
	long findByIdRequestAndStatusCart(User user);
}