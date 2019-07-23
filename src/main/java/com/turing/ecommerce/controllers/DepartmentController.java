package com.turing.ecommerce.controllers;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.turing.ecommerce.DTO.DepartmentDTO;
import com.turing.ecommerce.exceptions.DepartmentNotFoundException;
import com.turing.ecommerce.model.Department;
import com.turing.ecommerce.service.DepartmentService;



/**
 * Department Controller for all Rest APIs endpoints related to department.
 * 
 * @author ThankGod Ukachukwu
 *
 */


@RestController
public class DepartmentController {

	@Resource(name = "departmentImplService")
	private DepartmentService departmentService;

	/*
	 * API to return all departments
	 */
	@GetMapping(path = "/api/departments")
	public ResponseEntity<List<DepartmentDTO>> getAll() {
		return ResponseEntity.ok(departmentService.getAll());
	}

	/*
	 * API to return a department
	 */
	@GetMapping(path = "/api/departments/{department_id}")
	public ResponseEntity<DepartmentDTO> getDepartmentById(
			@PathVariable(name = "department_id", required = true) Integer departmentId) {
		Optional<DepartmentDTO> department = departmentService.findById(departmentId);

		
		
		if (!department.isPresent()) {
			
			throw new DepartmentNotFoundException(departmentId);
		}

		return ResponseEntity.ok(department.get());

	}

}
