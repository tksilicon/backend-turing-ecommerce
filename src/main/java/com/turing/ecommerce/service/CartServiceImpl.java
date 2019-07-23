/**
 * 
 */
package com.turing.ecommerce.service;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turing.ecommerce.DTO.ItemForm;
import com.turing.ecommerce.DTO.ProductDetailsDTO;
import com.turing.ecommerce.DTO.SavedItem;
import com.turing.ecommerce.DTO.ShoppingCartProduct;
import com.turing.ecommerce.model.Order;
import com.turing.ecommerce.model.Product;
import com.turing.ecommerce.model.ShoppingCart;
import com.turing.ecommerce.repository.CartRepository;
import com.turing.ecommerce.repository.OrderRepository;
import com.turing.ecommerce.repository.ProductRepository;

/**
 * @author frankukachukwu
 *
 */
@Service("cartServiceImpl")
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepository cartRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	OrderRepository orderRepo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.turing.ecommerce.service.CartService#save(com.turing.ecommerce.model.
	 * ShoppingCart)
	 */
	@Override
	public ShoppingCart save(ShoppingCart cart) {

		return cartRepository.save(cart);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.turing.ecommerce.service.CartService#getShoppingCartProducts(java.lang.
	 * Integer)
	 */
	@Transactional
	@Override
	public List<ShoppingCartProduct> getShoppingCartProducts(ShoppingCart cart) {

		List<ShoppingCartProduct> shoppingCartToReturn = new LinkedList<ShoppingCartProduct>();

		ShoppingCart sp = cartRepository.save(cart);

		List<ShoppingCart> cartNow = cartRepository.findByCartId(cart.getCartId());

		Iterator<ShoppingCart> cartIt = cartNow.iterator();

		while (cartIt.hasNext()) {
			ShoppingCart sc = cartIt.next();

			ShoppingCartProduct scp = new ShoppingCartProduct();

			Optional<ProductDetailsDTO> pdo = productRepository.getProductDetails(sc.getProductId());

			scp.setItemId(sc.getItemId());
			scp.setName(pdo.get().getName());
			scp.setAttributes(sc.getAttributes());
			scp.setProductId(sc.getProductId());
			scp.setUnitCost(pdo.get().getPrice());
			scp.setQuantity(1);
			scp.setImage(pdo.get().getImage1());

			BigDecimal total = scp.getUnitCost().multiply(new BigDecimal(scp.getQuantity()));
			scp.setSubtotal(total);

			shoppingCartToReturn.add(scp);

		}

		return shoppingCartToReturn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.turing.ecommerce.service.CartService#getShoppingCartProducts2(java.lang.
	 * String)
	 */
	@Transactional
	@Override
	public List<ShoppingCartProduct> getShoppingCartProducts2(String cartId) {
		List<ShoppingCart> cartNow = cartRepository.findByCartId(cartId);

		List<ShoppingCartProduct> shoppingCartToReturn = new LinkedList<ShoppingCartProduct>();
		Iterator<ShoppingCart> cartIt = cartNow.iterator();

		while (cartIt.hasNext()) {
			ShoppingCart sc = cartIt.next();

			ShoppingCartProduct scp = new ShoppingCartProduct();

			Optional<ProductDetailsDTO> pdo = productRepository.getProductDetails(sc.getProductId());

			scp.setItemId(sc.getItemId());
			scp.setName(pdo.get().getName());
			scp.setAttributes(sc.getAttributes());
			scp.setProductId(sc.getProductId());
			scp.setUnitCost(pdo.get().getPrice());
			scp.setQuantity(1);
			scp.setImage(pdo.get().getImage1());

			BigDecimal total = scp.getUnitCost().multiply(new BigDecimal(scp.getQuantity()));
			scp.setSubtotal(total);

			shoppingCartToReturn.add(scp);

		}

		return shoppingCartToReturn;
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.turing.ecommerce.service.CartService#moveItemToCart(java.lang.Integer)
	 */
	@Transactional
	@Override
	public void moveItemToCart(Integer itemId) {
		
		cartRepository.itemToCart(itemId);
		

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.turing.ecommerce.service.CartService#returnTotalAmountCart(java.lang.
	 * Integer)
	 */
	@Override
	public BigDecimal returnTotalAmountCart(String cartId) {
		
		
		List<ShoppingCart> cartNow = cartRepository.findByCartId(cartId);

		
		Iterator<ShoppingCart> cartIt = cartNow.iterator();
		
		BigDecimal total  = new BigDecimal(0.00);

		while (cartIt.hasNext()) {
			ShoppingCart sc = cartIt.next();

			

			Optional<ProductDetailsDTO> pdo = productRepository.getProductDetails(sc.getProductId());
			
			BigDecimal bd = pdo.get().getPrice();
			Integer quant = sc.getQuantity();
            

			BigDecimal itemtotal = bd.multiply(new BigDecimal(quant));
			total.add(itemtotal);

			

		}

		return total;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.turing.ecommerce.service.CartService#saveForLater(java.lang.Integer)
	 */
	@Override
	public void saveForLater(Integer itemId) {
		
		 cartRepository.saveItemForLater(itemId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.turing.ecommerce.service.CartService#getSaved(java.lang.Integer)
	 */
	@Override
	public List<SavedItem> getSaved(String cartId) {
		
		return cartRepository.getSavedItems(cartId);
	}

	

	
	/*
	 * (non-Javadoc)
	 * 
	 * Update quantity of shopping cart item and return cart
	 * 
	 * @see com.turing.ecommerce.service.CartService#getSavedItemInCart(java.lang.
	 * Integer)
	 */
	@Transactional
	@Override
	public List<ShoppingCartProduct> getSavedItemInCart(int itemId, ItemForm quant) {

		ShoppingCart scc = cartRepository.findByItemId(itemId);

		scc.setQuantity(scc.getQuantity() + quant.getQuantity());

		cartRepository.save(scc);

		List<ShoppingCart> cartNow = cartRepository.findByCartId(scc.getCartId());

		List<ShoppingCartProduct> shoppingCartToReturn = new LinkedList<ShoppingCartProduct>();
		Iterator<ShoppingCart> cartIt = cartNow.iterator();

		while (cartIt.hasNext()) {
			ShoppingCart sc = cartIt.next();

			ShoppingCartProduct scp = new ShoppingCartProduct();

			Optional<ProductDetailsDTO> pdo = productRepository.getProductDetails(sc.getProductId());

			scp.setItemId(sc.getItemId());
			scp.setName(pdo.get().getName());
			scp.setAttributes(sc.getAttributes());
			scp.setProductId(sc.getProductId());
			scp.setUnitCost(pdo.get().getPrice());
			scp.setQuantity(sc.getQuantity());
			scp.setImage(pdo.get().getImage1());

			BigDecimal total = scp.getUnitCost().multiply(new BigDecimal(scp.getQuantity()));
			scp.setSubtotal(total);

			shoppingCartToReturn.add(scp);

		}

		return shoppingCartToReturn;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * Empty Cart
	 * 
	 * @see com.turing.ecommerce.service.CartService#delete(java.lang.
	 * String)
	 */
	
	@Transactional
	@Override
	public List<ShoppingCartProduct> delete(String cartId) {
		
		List<ShoppingCart> cartNow = cartRepository.findByCartId(cartId);
		
		Iterator<ShoppingCart> cartIt = cartNow.iterator();
		
		while (cartIt.hasNext()) {
			ShoppingCart sc = cartIt.next();

			cartRepository.delete(sc);

		}
		
		List<ShoppingCartProduct> shoppingCartToReturn = new LinkedList<ShoppingCartProduct>();

		return shoppingCartToReturn;
	}

	/* (non-Javadoc)
	 * @see com.turing.ecommerce.service.CartService#updateCart(java.lang.Integer)
	 */
	@Override
	public void updateCart(Integer itemId, String cartId) {
		cartRepository.removeItem(itemId, null);
		
	}

	/* (non-Javadoc)
	 * @see com.turing.ecommerce.service.CartService#getProduct(java.lang.Integer)
	 */
	@Override
	public Optional<ProductDetailsDTO> getProduct(Integer productId) {
		// TODO Auto-generated method stub
		return productRepository.getProductDetails(productId);
	}
	
	
	
	
	

}
