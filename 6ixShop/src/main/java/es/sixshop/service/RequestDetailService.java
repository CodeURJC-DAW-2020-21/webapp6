package es.sixshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sixshop.model.RequestDetail;
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
}
