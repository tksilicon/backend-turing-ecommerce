/**
 * 
 */
package com.turing.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.turing.ecommerce.DTO.SavedItem;
import com.turing.ecommerce.model.Order;
import com.turing.ecommerce.model.ShoppingCart;

/**
 * @author thankgodukachukwu
 *
 */
public interface CartRepository extends CrudRepository<ShoppingCart, Integer> {
	
	
	
	@Query("select max(s.itemId) FROM ShoppingCart s")
	public Integer findMaxItemId();
	
	public List<ShoppingCart> findByCartId(String cartId);
	public ShoppingCart findByItemId(int itemId);
	

	@Query("UPDATE ShoppingCart s SET s.buyNow = 1 where s.itemId = :itemId")
	public void itemToCart(Integer itemId);
	
	@Query("UPDATE ShoppingCart s SET s.buyNow = 0 where s.itemId = :itemId")
	public void saveItemForLater(Integer itemId);
	
	
	@Query("select new com.turing.ecommerce.DTO.SavedItem (a.itemId, p.name, a.attributes, p.price) "
			+ " FROM ShoppingCart a inner join Product p on a.productId = p.productId where a.buyNow <> 1 and a.cartId = :cartId")
	public List<SavedItem> getSavedItems(String cartId);
	
	

	@Query("UPDATE ShoppingCart s SET s.cartId = :cartId where s.itemId = :itemId")
	public void removeItem(Integer itemId, String cartId);
           
	
	
	
	

}
