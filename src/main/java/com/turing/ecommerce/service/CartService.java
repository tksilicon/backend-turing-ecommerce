/**
 * 
 */
package com.turing.ecommerce.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.turing.ecommerce.DTO.ItemForm;
import com.turing.ecommerce.DTO.ProductDetail;
import com.turing.ecommerce.DTO.SavedItem;
import com.turing.ecommerce.DTO.ShoppingCartForm;
import com.turing.ecommerce.DTO.CartWithProduct;
import com.turing.ecommerce.model.ShoppingCart;

/**
 * @author thankgodukachukwu
 *
 */
public interface CartService {
	
	public ShoppingCart save(ShoppingCart cart);
	public List<CartWithProduct> getShoppingCartProducts(ShoppingCartForm cart);
	public List<CartWithProduct> getShoppingCartProducts2(String cartId);
	public List<CartWithProduct> getSavedItemInCart(int itemId, ItemForm quant);
	public List<CartWithProduct> delete(String cartId);
	public void moveItemToCart(Integer itemId);
	public BigDecimal returnTotalAmountCart(String cardId);
	public void saveForLater(Integer itemId);
	public List<SavedItem> getSaved(String cartId);
	public void updateCart(Integer itemId, String cartId);
	public Optional<ProductDetail>  getProduct(Integer productId);
	
	
	
	
	
	
	
}
