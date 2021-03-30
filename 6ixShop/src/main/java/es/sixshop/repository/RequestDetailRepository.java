package es.sixshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.sixshop.model.RequestDetail;

public interface RequestDetailRepository extends JpaRepository<RequestDetail, Long>{
	
}