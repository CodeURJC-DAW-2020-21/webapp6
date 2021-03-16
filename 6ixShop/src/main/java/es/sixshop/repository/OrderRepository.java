package es.sixshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.sixshop.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	
}
