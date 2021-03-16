package es.sixshop.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sixshop.model.Order;
import es.sixshop.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderR;
	
	public Collection<Order> findAll(){
		return orderR.findAll();
	}
	
	public Optional<Order> findById(long id){
		return orderR.findById(id);
	}
	
	public void save(Order order) {
		orderR.save(order);
	}

}
