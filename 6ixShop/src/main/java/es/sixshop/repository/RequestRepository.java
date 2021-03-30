package es.sixshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.sixshop.model.Request;

public interface RequestRepository extends JpaRepository<Request, Long>{
	
}