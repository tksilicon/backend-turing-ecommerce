/**
 * 
 */
package com.turing.ecommerce.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.turing.ecommerce.DTO.CustomerOrderDTO;
import com.turing.ecommerce.DTO.OrderDTO;
import com.turing.ecommerce.model.Order;

/**
 * @author frankukachukwu
 *
 */
public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	
	@Query("select new com.turing.ecommerce.DTO.OrderDTO(o.orderId, o.authCode, o.comments,o.createdOn, "
			+ " o.customerId, o.reference, o.shippedOn,"
			+ "o.shippingId, o.status, o.taxId, o.totalAmount)  "
			+ " FROM Order o inner join Customer c on o.customerId = c.customerId where o.customerId= :customerId")
	List<OrderDTO> findOrderByCustomerId(Integer customerId);
	
	
	
	@Query("select new com.turing.ecommerce.DTO.CustomerOrderDTO( o.orderId, o.totalAmount,"
			+ " o.createdOn, o.shippedOn, o.status, c.name) FROM Order " 
			+ "	o inner join Customer c on o.customerId = c.customerId WHERE o.orderId = :orderId")
	Optional<CustomerOrderDTO> findByOrderIdShortDetails(Integer orderId);
	
	
	
	
	
	
	


}
