package es.sixshop.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import es.sixshop.model.Request;
import es.sixshop.model.RequestDetail;

public interface RequestDetailRepository extends JpaRepository<RequestDetail, Long>{
	Collection<RequestDetail> findByRequest(Request request);
}