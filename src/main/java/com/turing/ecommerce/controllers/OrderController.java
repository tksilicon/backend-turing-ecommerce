/**
 * 
 */
package com.turing.ecommerce.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.turing.ecommerce.DTO.CustomerOrderDTO;
import com.turing.ecommerce.DTO.OrderDTO;
import com.turing.ecommerce.DTO.OrderForm;
import com.turing.ecommerce.exceptions.OrderDetailNotFoundException;
import com.turing.ecommerce.model.Customer;
import com.turing.ecommerce.model.Order;
import com.turing.ecommerce.model.OrderDetail;
import com.turing.ecommerce.service.CustomerService;
import com.turing.ecommerce.service.OrdersService;

import io.swagger.annotations.Api;

/**
 * 
 * Order Controller for all Rest APIs endpoints related to Order.
 * 
 * @author thankgodukachukwu
 *
 */
@Api(value = "Everything about Orders")
@RestController
public class OrderController {

	@Resource(name = "ordersServiceImpl")
	private OrdersService ordersService;
	
	@Resource(name = "customerImplService")
	private CustomerService customerService;

	
	/**
	 * 
	 * @param order
	 * @return
	 */
	@PostMapping(path = "/api/orders")
	public ResponseEntity<Map<String, String>> createCustomer(@Valid @RequestBody OrderForm order) {

		Order ordex = new Order();
		ordex.setShippingId(order.getShipppingId());
		ordex.setTaxId(order.getTaxId());

		Order orderCreated = ordersService.save(ordex);

		Map<String, String> map = new HashMap<>();
		map.put("orderId", String.valueOf(orderCreated.getOrderId()));

		return ResponseEntity.ok(map);

	}
	/**
	 * 
	 * @param orderId
	 * @return
	 */

	@GetMapping("/api/orders/{order_id}")
	public ResponseEntity<Optional<OrderDetail>> getOrderDetail(
			@PathVariable(name = "order_id", required = true) Integer orderId) {

		try {

			return ResponseEntity.ok(ordersService.findOrderById(orderId));

		} catch (IllegalArgumentException ex) {
			throw new OrderDetailNotFoundException("Endpoint not found.");
		}

	}
	/**
	 * 
	 * @param authentication
	 * @return
	 */
	
	@GetMapping("/api/orders/inCustomer")
	public ResponseEntity<List<OrderDTO>> getOrderInCustomer(Authentication authentication) {

		try {
           Optional<Customer> cust = customerService.findByEmail(authentication.getName());
           
           
			return ResponseEntity.ok(ordersService.getCustomersOrders(cust.get().getCustomerId()));

		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Token expired");
		}


	}
	/**
	 * 
	 * @param orderId
	 * @return
	 */
	
	@GetMapping("/api/orders/shortDetail/{order_id}")
	public ResponseEntity<Optional<CustomerOrderDTO>> getOrderShortDetail(
			@PathVariable(name = "order_id", required = true) Integer orderId) {


		return ResponseEntity.ok(ordersService.findOrderShortDetails(orderId));

		

	}


}
