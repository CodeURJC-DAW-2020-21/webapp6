package es.sixshop.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sixshop.model.RequestDetail;
import es.sixshop.model.Product;
import es.sixshop.model.Request;
import es.sixshop.repository.RequestDetailRepository;

@Service
public class RequestDetailService {
	
	@Autowired
	private RequestDetailRepository requestDetailR;
	
	public Optional<RequestDetail> findById(long id) {
		return requestDetailR.findById(id);
	}

	public List<RequestDetail> findAll() {
		return requestDetailR.findAll();
	}

	public void save(RequestDetail requestDetail) {
		requestDetailR.save(requestDetail);
	}

	public void delete(long id) {
		requestDetailR.deleteById(id);
	}
	
	/**
	 * Devuelve una lista de productos pertenecientes al carrito de un usuario
	 * 
	 * @param request Pedido al que pertenecen los productos
	 * @return Lista de productos
	 */
	public Collection<Product> findProductOfRequestDetail(Request request){
		Collection<Product> products = new ArrayList<Product>();
		
		//Devuelve todos los detallesPedido de dicho Pedido para obtener los productos
		Collection<RequestDetail> requestDetail = requestDetailR.findByRequest(request);
		//Almacena los productos de dichos detallesPedido
		for (RequestDetail objRequestDetail : requestDetail) {
			products.add(objRequestDetail.getProduct());
		}
		
		return products;
	}
	
	public Collection<RequestDetail> findByRequest(Request request){
		return requestDetailR.findByRequest(request);
	}
}
