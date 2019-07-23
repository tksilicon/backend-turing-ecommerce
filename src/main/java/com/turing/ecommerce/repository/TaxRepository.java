/**
 * 
 */
package com.turing.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.turing.ecommerce.model.Tax;

/**
 * @author thankgodukachukwu
 *
 */
public interface TaxRepository extends JpaRepository<Tax, Integer> {
	
	

}
