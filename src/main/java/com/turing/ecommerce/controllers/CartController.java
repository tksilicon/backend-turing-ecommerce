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
import javax.persistence.EntityNotFoundException;
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
import com.turing.ecommerce.DTO.ProductDetail;
import com.turing.ecommerce.DTO.SavedItem;
import com.turing.ecommerce.DTO.ShoppingCartForm;
import com.turing.ecommerce.DTO.CartWithProduct;
import com.turing.ecommerce.exceptions.CartNotFoundException;
import com.turing.ecommerce.exceptions.CustomerExistException;
import com.turing.ecommerce.exceptions.CustomerNotFoundException;
import com.turing.ecommerce.exceptions.ItemNotFoundException;
import com.turing.ecommerce.exceptions.ProductNotFoundException;
import com.turing.ecommerce.model.Attribute;
import com.turing.ecommerce.model.Customer;
import com.turing.ecommerce.model.ShoppingCart;
import com.turing.ecommerce.service.CartService;
import com.turing.ecommerce.utils.Uid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;;

/**
 * @author thankgodukachukwu
 *
 */
@Api(value = "Everything about Shopping Cart")
@RestController
public class CartController {

	@Resource(name = "cartServiceImpl")
	private CartService cartService;
	
	public static final String CARDNOTFOUND = "Cart Not found";
	public static final String ITEMNOTFOUND = "Item Not found";

	/**
	 * Generate Card Id Unique ID
	 * 
	 * @return
	 */
	@GetMapping(path = "/api/shoppingcart/generateUniqueId")
	public ResponseEntity<Map<String, String>> getCartId() {

		String str = Uid.generateRandomId(11, "abcdefghjkmnpqrstuvwxyz23456789", Character.LOWERCASE_LETTER);
		Map<String, String> model = new HashMap<>();
		model.put("cart_id", str);
		return ResponseEntity.ok(model);
	}

	/*
	 * API endpoint to add to shopping cart
	 */
	@PostMapping(path = "/api/shoppingcart/add")
	public ResponseEntity<List<CartWithProduct>> addToShoppingCart(@Valid @RequestBody ShoppingCartForm cart) {

		

		ShoppingCart scp = new ShoppingCart();
		scp.setCartId(cart.getCartId());
		scp.setProductId(cart.getProductId());
		scp.setAttributes(cart.getAttributes());

		Date date = new Date();

		scp.setAddedOn(date);

		List<CartWithProduct> ourCart = cartService.getShoppingCartProducts(cart);

		return ResponseEntity.ok(ourCart);

	}

	/**
	 * Get items in cart
	 * 
	 * @return
	 */
	@GetMapping(path = "/api//shoppingcart/{cart_id}")
	public ResponseEntity<List<CartWithProduct>> getShoppingCart(
			@PathVariable(name = "cart_id", required = true) String cartId) {

		try {
			return ResponseEntity.ok(cartService.getShoppingCartProducts2(cartId));
		} catch (EntityNotFoundException ex) {
			throw new CartNotFoundException(CARDNOTFOUND);

		}

	}

	/*
	 * API endpoint to update Item in cart
	 */
	@PutMapping(path = "/api/shoppingcart/update/{item_id}")
	public ResponseEntity<List<CartWithProduct>> updateItemInCart(
			@PathVariable(name = "item_id", required = true) Integer itemId, @Valid @RequestBody ItemForm quant) {
		try {
			return ResponseEntity.ok(cartService.getSavedItemInCart(itemId, quant));
		} catch (EntityNotFoundException ex) {
			throw new ItemNotFoundException(CARDNOTFOUND);

		}
	}

	/*
	 * API endpoint to delete Items in cart
	 */
	@DeleteMapping(path = "/api/shoppingcart/empty/{cart_id}")
	public ResponseEntity<List<CartWithProduct>> emptyCart(
			@PathVariable(name = "cart_id", required = true) String cartId) {

		List<CartWithProduct> returnCart = null;

		try {
			returnCart = cartService.delete(cartId);
			return ResponseEntity.ok(returnCart);
		} catch (EntityNotFoundException ex) {
			throw new CartNotFoundException(CARDNOTFOUND);

		}

	}

	/*
	 * API endpoint to move item to cart
	 */
	@GetMapping(path = "/api/shoppingcart/moveToCart/{item_id}")
	public ResponseEntity moveToCart(@PathVariable(name = "item_id", required = true) Integer itemId) {

		try {
			cartService.moveItemToCart(itemId);

			return ResponseEntity.ok().build();
		} catch (EntityNotFoundException ex) {
			throw new ItemNotFoundException(ITEMNOTFOUND);

		}

	}

	/*
	 * API endpoint to get total amount
	 */
	@GetMapping(path = "/api/shoppingcart/totalAmount/{cart_id}")
	public ResponseEntity<Map<String, BigDecimal>> getTotalAmount(
			@PathVariable(name = "cart_id}", required = true) String cartId) {

		BigDecimal dec = cartService.returnTotalAmountCart(cartId);
		Map<String, BigDecimal> map = new HashMap<>();
		map.put("total_amount", dec);
		try {
			return ResponseEntity.ok(map);

		} catch (EntityNotFoundException ex) {
			throw new CartNotFoundException(CARDNOTFOUND);

		}

	}

	/*
	 * API endpoint to get total amount
	 */
	@GetMapping(path = "/api/shoppingcart/saveForLater/{item_id}")
	public ResponseEntity saveForLater(@PathVariable(name = "item_id}", required = true) Integer itemId) {

		cartService.saveForLater(itemId);
		try {
			return ResponseEntity.ok().build();
		} catch (EntityNotFoundException ex) {
			throw new ItemNotFoundException(ITEMNOTFOUND);

		}

	}

	/*
	 * API endpoint to get SavedItems
	 */
	@GetMapping(path = "/api/shoppingcart/getSaved/{cart_id}")
	public ResponseEntity<List<SavedItem>> getSaved(@PathVariable(name = "cart_id", required = true) String cartId) {
		try {
			return ResponseEntity.ok(cartService.getSaved(cartId));
		} catch (EntityNotFoundException ex) {
			throw new CartNotFoundException(CARDNOTFOUND);

		}

	}

	/*
	 * API endpoint to remove Items in cart
	 */
	@PutMapping(path = "/api/shoppingcart/removeProduct/{item_id}")
	public ResponseEntity<List<CartWithProduct>> removeProduct(
			@PathVariable(name = "item_id", required = true) Integer itemId) {

		try {
			cartService.updateCart(itemId, null);
			return ResponseEntity.ok().build();
		} catch (EntityNotFoundException ex) {
			throw new ItemNotFoundException(ITEMNOTFOUND);

		}

	}

}
