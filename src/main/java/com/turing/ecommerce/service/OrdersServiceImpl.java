/**
 * 
 */
package com.turing.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.ecommerce.DTO.CustomerOrderDTO;
import com.turing.ecommerce.DTO.OrderDTO;
import com.turing.ecommerce.model.Order;
import com.turing.ecommerce.model.OrderDetail;
import com.turing.ecommerce.repository.OrderDetailRepository;
import com.turing.ecommerce.repository.OrderRepository;

/**
 * @author thankgodukachukwu
 *
 */

/**
 * OrderServiceImpl for department related handling.
 * @author ThankGod Ukachukwu
 *
 */
@Service("ordersServiceImpl") 
public class OrdersServiceImpl implements OrdersService {
	
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;

	/* (non-Javadoc)
	 * @see com.turing.ecommerce.service.OrdersService#save(com.turing.ecommerce.model.Order)
	 */
	@Override
	public Order save(Order order) {
		
		return orderRepository.save(order);
	}

	/* (non-Javadoc)
	 * @see com.turing.ecommerce.service.OrdersService#findOrderById(java.lang.Integer)
	 */
	@Override
	public Optional<OrderDetail> findOrderById(Integer orderId) {
		
		return orderDetailRepository.findById(orderId);
	}

	/* (non-Javadoc)
	 * @see com.turing.ecommerce.service.OrdersService#getCustomersOrders(java.lang.String)
	 */
	@Override
	public List<OrderDTO> getCustomersOrders(Integer customerId) {
		
		return orderRepository.findOrderByCustomerId(customerId);
	}

	/* (non-Javadoc)
	 * @see com.turing.ecommerce.service.OrdersService#findOrderShortDetails(java.lang.Integer)
	 */
	@Override
	public Optional<CustomerOrderDTO> findOrderShortDetails(Integer orderId) {
		
		return orderRepository.findByOrderIdShortDetails(orderId);
	}

}
