/**
 * 
 */
package com.turing.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;

import com.turing.ecommerce.model.OrderDetail;

/**
 * @author thankgodukachukwu
 *
 */
public interface OrderDetailRepository extends CrudRepository<OrderDetail, Integer> {

}
