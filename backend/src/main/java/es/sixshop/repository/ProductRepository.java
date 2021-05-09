package es.sixshop.repository;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.sixshop.model.Product;
import es.sixshop.model.User;

public interface ProductRepository extends JpaRepository<Product, Long>{
	Product findByIdProduct(long idProduct);
	Collection<Product> findByUserAndVisible(User user, boolean visible);
	Page<Product> findByCategoryAndVisible(String category, Pageable pageable, boolean visible);
	Page<Product> findByVisible(Pageable pageable, boolean visible);
	Product findByIdProductAndVisible(Long idProduct, boolean visible);
	
	@Query("SELECT PR FROM Product PR WHERE PR.rating>=0 AND PR.visible=true ORDER BY PR.rating DESC")
	Page<Product> findByRatingAndVisible(Pageable pageable);
	
	@Query("SELECT COUNT(*) FROM Product PR, User US, Request REQ, RequestDetail REQDET WHERE PR.user=US.idUser AND PR.idProduct=REQDET.product AND REQDET.request=REQ.idRequest  AND US.idUser=?3 AND REQ.status='PAID' AND  REQ.date between ?1 AND ?2")
    Integer findByMonthSales(Date month1, Date month2,long idUser);
	
	@Query("SELECT COUNT(*) FROM Product PR, User US, Request REQ, RequestDetail REQDET WHERE PR.user=US.idUser AND PR.idProduct=REQDET.product AND REQDET.request=REQ.idRequest  AND US.idUser=?1 AND REQ.status='PAID'")
    Integer findBySoldProducts(long idUser);
}
