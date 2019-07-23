/**
 * 
 */
package com.turing.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.turing.ecommerce.DTO.CustomerOrderDTO;
import com.turing.ecommerce.DTO.OrderDTO;
import com.turing.ecommerce.model.Order;
import com.turing.ecommerce.model.OrderDetail;

/**
 * @author thankgodukachukwu
 *
 */
public interface OrdersService {

	
	public Order save(Order order);
	public Optional <OrderDetail> findOrderById(Integer orderId);
	public List<OrderDTO> getCustomersOrders(Integer customerId);
	public Optional <CustomerOrderDTO> findOrderShortDetails(Integer orderId);
	
	
}
