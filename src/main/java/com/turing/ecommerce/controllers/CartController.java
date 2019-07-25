/**
 * 
 */
package com.turing.ecommerce.controllers;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.turing.ecommerce.DTO.CustomerForm;
import com.turing.ecommerce.DTO.CustomerUpdateForm;
import com.turing.ecommerce.DTO.ItemForm;
import com.turing.ecommerce.DTO.ProductDetailsDTO;
import com.turing.ecommerce.DTO.SavedItem;
import com.turing.ecommerce.DTO.ShoppingCartForm;
import com.turing.ecommerce.DTO.ShoppingCartProduct;
import com.turing.ecommerce.exceptions.CustomerExistException;
import com.turing.ecommerce.exceptions.CustomerNotFoundException;
import com.turing.ecommerce.exceptions.ProductNotFoundException;
import com.turing.ecommerce.model.Attribute;
import com.turing.ecommerce.model.Customer;
import com.turing.ecommerce.model.ShoppingCart;
import com.turing.ecommerce.service.CartService;
import com.turing.ecommerce.utils.Uid;;

/**
 * @author frankukachukwu
 *
 */
@RestController
public class CartController {

	@Resource(name = "CartServiceImpl")
	private CartService cartService;

	/**
	 * Generate Card Id Unique ID
	 * 
	 * @return
	 */
	@GetMapping(path = "/api/shoppingcart/generateUniqueId")
	public ResponseEntity<Map<String, String>> getAll() {

		String str = Uid.generateRandomId(11, "abcdefghjkmnpqrstuvwxyz23456789", Character.LOWERCASE_LETTER);
		Map<String, String> model = new HashMap<String, String>();
		model.put("cart_id", str);
		return ResponseEntity.ok(model);
	}

	/*
	 * API endpoint to add to shopping cart
	 */
	@PostMapping(path = "/api/shoppingcart/add")
	public ResponseEntity<List<ShoppingCartProduct>> addToShoppingCart(@Valid @RequestBody ShoppingCartForm cart)
			throws CustomerExistException {

		ProductDetailsDTO pdo = cartService.getProduct(cart.getProductId())
				.orElseThrow(() -> new ProductNotFoundException("Product Not found"));

		ShoppingCart scp = new ShoppingCart();
		scp.setCartId(cart.getCartId());
		scp.setProductId(cart.getProductId());
		scp.setAttributes(cart.getAttributes());

		Date date = new Date();

		scp.setAddedOn(date);

		List<ShoppingCartProduct> ourCart = cartService.getShoppingCartProducts(scp);

		return ResponseEntity.ok(ourCart);

	}

	/**
	 * Get items in cart
	 * 
	 * @return
	 */
	@GetMapping(path = "/api//shoppingcart/{cart_id}")
	public ResponseEntity<List<ShoppingCartProduct>> getShoppingCart(
			@PathVariable(name = "cart_id", required = true) String cartId) {

		return ResponseEntity.ok(cartService.getShoppingCartProducts2(cartId));
	}

	/*
	 * API endpoint to update Item in cart
	 */
	@PutMapping(path = "/api/shoppingcart/update/{item_id}")
	public ResponseEntity<List<ShoppingCartProduct>> updateItemInCart(
			@PathVariable(name = "item_id", required = true) Integer itemId, @Valid @RequestBody ItemForm quant) {

		return ResponseEntity.ok(cartService.getSavedItemInCart(itemId, quant));

	}

	/*
	 * API endpoint to delete Items in cart
	 */
	@DeleteMapping(path = "/api/shoppingcart/empty/{cart_id}")
	public ResponseEntity<List<ShoppingCartProduct>> emptyCart(
			@PathVariable(name = "cart_id", required = true) String cartId) {

		List<ShoppingCartProduct> returnCart = null;

		try {
			returnCart = cartService.delete(cartId);
			return ResponseEntity.ok(returnCart);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.ok(returnCart);
		}

	}
	
	/*
	 * API endpoint to move item to cart
	 */
	@GetMapping(path = "/api/shoppingcart/moveToCart/{item_id}")
	public ResponseEntity moveToCart(
			@PathVariable(name = "item_id", required = true) Integer itemId) {

		

		try {
			 cartService.moveItemToCart(itemId);
			 
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.ok().build();
		}

	}
	
	/*
	 * API endpoint to get total amount
	 */
	@GetMapping(path = "/api/shoppingcart/totalAmount/{cart_id}")
	public ResponseEntity<Map<String, BigDecimal>> getTotalAmount(
			@PathVariable(name = "cart_id}", required = true) String cartId) {

		BigDecimal dec = cartService.returnTotalAmountCart(cartId);
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		map.put("total_amount", dec);
		
		return ResponseEntity.ok(map);
	}
	
	/*
	 * API endpoint to get total amount
	 */
	@GetMapping(path = "/api/shoppingcart/saveForLater/{item_id}")
	public ResponseEntity saveForLater(
			@PathVariable(name = "item_id}", required = true) Integer itemId) {

		cartService.saveForLater(itemId);
		
		return ResponseEntity.ok().build();
	}
	
	
	/*
	 * API endpoint to get SavedItems
	 */
	@GetMapping(path = "/api/shoppingcart/getSaved/{cart_id}")
	public ResponseEntity<List<SavedItem>> getSaved(
			@PathVariable(name = "cart_id", required = true) String cartId) {

		
		
		return ResponseEntity.ok(cartService.getSaved(cartId));
	}
	
	
	/*
	 * API endpoint to remove Items in cart
	 */
	@PutMapping(path = "/api/shoppingcart/removeProduct/{item_id}")
	public ResponseEntity<List<ShoppingCartProduct>> removeProduct(
			@PathVariable(name = "item_id", required = true) Integer itemId) {

		

		try {
			cartService.updateCart(itemId, null);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.ok().build();
		}

	}
	
	
	

	
	

}
