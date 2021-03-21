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
	
	
//	@PostConstruct //Se ejecuta después de haber inyectado las dependencias.
//	public void init(){
//		save(new Product("Shameless","Description of Shameless","Series",50,6));
//		save(new Product("Pablo Simeone","Description of Pablo Simeone","Custom",30,6));
//		save(new Product("True Detective","Description of True Detective","Series",40,7));
//		save(new Product("Gran Torino","Description of Gran Torino","Movies",40,8));
//	}
	
	
	
	public Collection<Order> findAll(){
		return orderR.findAll();
	}
	
	public Optional<Order> findById(long id){
		return orderR.findById(id);
	}
	
	public void save(Order order){
		orderR.save(order);
	}
}
