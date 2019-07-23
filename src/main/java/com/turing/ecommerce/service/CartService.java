/**
 * 
 */
package com.turing.ecommerce.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.turing.ecommerce.DTO.ItemForm;
import com.turing.ecommerce.DTO.ProductDetailsDTO;
import com.turing.ecommerce.DTO.SavedItem;
import com.turing.ecommerce.DTO.ShoppingCartProduct;
import com.turing.ecommerce.model.ShoppingCart;

/**
 * @author thankgodukachukwu
 *
 */
public interface CartService {
	
	public ShoppingCart save(ShoppingCart cart);
	public List<ShoppingCartProduct> getShoppingCartProducts(ShoppingCart cart);
	public List<ShoppingCartProduct> getShoppingCartProducts2(String cartId);
	public List<ShoppingCartProduct> getSavedItemInCart(int itemId, ItemForm quant);
	public List<ShoppingCartProduct> delete(String cartId);
	public void moveItemToCart(Integer itemId);
	public BigDecimal returnTotalAmountCart(String cardId);
	public void saveForLater(Integer itemId);
	public List<SavedItem> getSaved(String cartId);
	public void updateCart(Integer itemId, String cartId);
	public Optional<ProductDetailsDTO>  getProduct(Integer productId);
	
	
	
	
	
	
	
}
