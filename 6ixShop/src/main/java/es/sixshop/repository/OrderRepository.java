package es.sixshop.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import es.sixshop.model.Order;
import es.sixshop.model.Product;

public interface OrderRepository extends JpaRepository<Order, Long>{
	Collection<Order> findByidOrder(long idOrder);
}