package com.turing.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.turing.ecommerce.DTO.DepartmentDTO;
import com.turing.ecommerce.model.Department;



/**
 * DepartmentService interface for all services related to Department.
 * @author ThankGod Ukachukwu
 *
 */

public interface DepartmentService {
	
	  
	  Optional<Department> findByName(String name);

	  Optional<DepartmentDTO> findById(Integer departmentId);
	  
	  public List<DepartmentDTO> getAll();
	  
	  
}



