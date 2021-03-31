package es.sixshop.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sixshop.model.Request;
import es.sixshop.model.User;
import es.sixshop.repository.RequestRepository;

@Service
public class RequestService {

	@Autowired
	private RequestRepository requestR;
	
	public Optional<Request> findById(long idRequest) {
		return requestR.findById(idRequest);
	}
	
	public boolean exist(long id) {
		return requestR.existsById(id);
	}

	public List<Request> findAll() {
		return requestR.findAll();
	}

	public void save(Request request) {
		requestR.save(request);
	}

	public void delete(long id) {
		requestR.deleteById(id);
	}
	
	//Devuelve el pedido del carrito (Solo debe existir uno por usuario)
	public Request findByBuyerUserAndStatus(User user, String status) {
		Collection<Request> requests = requestR.findByBuyerUserAndStatus(user, status);
		return requests.iterator().next();
	}
	
	//Devuelve todos los pedidos completados (Comprados)
	public Collection<Request> findByBuyerUserAndStatusPaid(User user) {
		Collection<Request> requests = requestR.findByBuyerUserAndStatus(user, "PAID");
		return requests;
	}
}
