package com.turing.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.turing.ecommerce.DTO.CategoryAllDTO;
import com.turing.ecommerce.DTO.CategoryDTO;
import com.turing.ecommerce.model.Category;
import com.turing.ecommerce.model.Department;

/**
 * Categories Repository is for all data access operations for Categories.
 * 
 * @author ThankGod Ukachukwu
 */
@RestResource(exported = false)
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	
	 /**
     * Retrieves a category by name.
     *
     * @param name of category 
     * @return the category details
     */
	@Query("select category from Category category where name =:name")
	Optional<Category> findByName(@Param("name") String name);

	
	
	
	/**
     * Retrieves a category given a department id.
     * 
     * select c.category_id, c.department_id, c.name, c.description from department d join 
     * category c on d.department_id = c.department_id where d.department_id = 2 
     *
     * @param department id to retrieve the details
     * @return the category details
     */
	@Query("SELECT new com.turing.ecommerce.DTO.CategoryDTO(c.categoryId,c.departmentId, c.name, c.description)  "
			+ " FROM Department d inner join d.category c on d.departmentId = c.departmentId where d.departmentId = :departmentId")
	public List<CategoryDTO> getCategoryByDepartmentId(@Param("departmentId") Integer departmentId);
	
	

	/**
     * Retrieves a categories.
     * 
     
     *
     * 
     * @return the list of category
     */
	@Query("SELECT new com.turing.ecommerce.DTO.CategoryAllDTO(c.categoryId,c.departmentId, c.name, c.description)  "
			+ " FROM Category c")
	public List<CategoryAllDTO> getAllOfCategory();
	
	
	

}
