/**
 * 
 */
package com.turing.ecommerce.controllers;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.turing.ecommerce.DTO.AuthenticationRequest;
import com.turing.ecommerce.DTO.CustomerAddressForm;
import com.turing.ecommerce.DTO.CustomerCreditCardForm;
import com.turing.ecommerce.DTO.CustomerForm;
import com.turing.ecommerce.DTO.CustomerUpdateForm;
import com.turing.ecommerce.exceptions.CustomerExistException;
import com.turing.ecommerce.exceptions.CustomerNotFoundException;
import com.turing.ecommerce.model.Customer;
import com.turing.ecommerce.repository.CustomerRepository;
import com.turing.ecommerce.security.jwt.JwtTokenProvider;
import com.turing.ecommerce.service.CustomCustomerDetailsService;
import com.turing.ecommerce.service.CustomerService;

/**
 * 
 * Customer Controller for all Rest APIs endpoints related to Products.
 * 
 * @author thankgodukachukwu
 *
 */
@RestController
public class CustomerController {

	@Resource(name = "customerImplService")
	private CustomerService customerService;

	@Resource(name = "customCustomerDetailsService") 
	private CustomCustomerDetailsService customCustomerDetailsService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	CustomerRepository users;

	/*
	 * API endpoint to update customers
	 */
	@PutMapping(path = "/api/customer")
	public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody CustomerUpdateForm cust) {

		try {
		Customer existed =

				customerService.findByEmail(cust.getEmail()).orElseThrow(() -> new CustomerNotFoundException());

		existed.setName(cust.getName());
		existed.setEmail(cust.getEmail());
		existed.setPassword(cust.getPassword());
		
		//existed.setPassword(passwordEncoder.encode(cust.getPassword()));
		existed.setDayPhone(cust.getDayPhone());
		existed.setEvePhone(cust.getEvePhone());
		existed.setMobPhone(cust.getMobPhone());
		
		return ResponseEntity.ok(customerService.save(existed));
		
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Token expired");
		}


	}
	


	@PostMapping("/api/customers/login")
	public ResponseEntity<Map<String, Object>> signin(@RequestBody AuthenticationRequest data) {

		try {

			String username = data.getUsername();
			
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
			String token = jwtTokenProvider.createToken(username, this.users.findByEmail(username)
					.orElseThrow(() -> new UsernameNotFoundException( "The email +" + username +" doesn't exist")).getRoles());
			
			
			
			Map<Object, Object> model = new HashMap<Object, Object>();
			model.put("username", username);
			model.put("token", token);

			Map<String, Object> mapResponse = new LinkedHashMap<String, Object>();

			Optional<Customer> cust = this.users.findByEmail(username);

			Map<String, Object> mapResponse2 = new LinkedHashMap<String, Object>();
			mapResponse2.put("schema", cust);

			int hours = (int) ((jwtTokenProvider.getValidityInMilliseconds() / (1000 * 60 * 60)) % 24);

			String output = "" + hours + "h";

			mapResponse.put("customer", mapResponse2);
			mapResponse.put("accessToken", token);
			mapResponse.put("expires_in", output);

			return ResponseEntity.ok(mapResponse);

		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Email or Password is invalid");
		}
	}

	/**@GetMapping("/api/customer")
	public ResponseEntity<Optional<Customer>> getCustomerById(HttpServletRequest request) throws Exception {

		String token = request.getParameter("USER-KEY");
		
		return ResponseEntity.ok(customerService.findByEmail(jwtTokenProvider.getAuthentication(token).getName()));

	}**/
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/api/customer")
    public ResponseEntity currentUser(@AuthenticationPrincipal UserDetails userDetails){
        Map<Object, Object> model = new HashMap<>();
        
        
        Optional<Customer> cust = this.users.findByEmail(userDetails.getUsername());
        model.put("customer", cust);
        model.put("username", userDetails.getUsername());
        model.put("roles", userDetails.getAuthorities()
            .stream()
            .map(a -> ((GrantedAuthority) a).getAuthority())
            .collect(toList())
        );
        return ok(model);
    }

	/*
	 * API endpoint to register customers
	 */
	@PostMapping(path = "/api/customers")
	public ResponseEntity registerCustomer(@Valid @RequestBody CustomerForm cust, 
			final BindingResult bindingResult)
			throws CustomerExistException {

		Optional<Customer> existed = customerService.findByEmail(cust.getEmail());

		if (existed.isPresent()) {
			throw new CustomerExistException("The email already exists.");
		}

		Customer existsx = new Customer();

		existsx.setName(cust.getName());
		existsx.setEmail(cust.getEmail());
		//existsx.setPassword(cust.getPassword());
		existsx.setPassword(passwordEncoder.encode(cust.getPassword()));
		

		customerService.save(existsx);

		Map<String, Object> mapResponse = new LinkedHashMap<String, Object>();

		Map<String, Object> mapResponse2 = new LinkedHashMap<String, Object>();

		mapResponse2.put("schema", existsx);
		mapResponse.put("customer", mapResponse2);

		String token = jwtTokenProvider.createToken(cust.getEmail(), Arrays.asList("USER"));

		//int hours = (int) ((jwtTokenProvider.getValidityInMilliseconds() / (1000 * 60 * 60)) % 24);
        //String output = "" + hours + "h";

		String username = cust.getEmail();
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, cust.getPassword()));

		mapResponse.put("accessToken","Bearer "+ token);
		mapResponse.put("expires_in", "24h");
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
	    headers.add("USER_KEY", "Bearer "+ token);
	         
	    
	    return ResponseEntity.ok()
	            .headers(headers)
	            .body(mapResponse);

		//return ResponseEntity.ok(mapResponse);

	}

	/*
	 * API endpoint to update customers address
	 */
	@PutMapping(path = "/api/customers/address")
	public ResponseEntity<Customer> updateCustomerAddress(@Valid @RequestBody CustomerAddressForm cust,
			@AuthenticationPrincipal UserDetails userDetails) {
		try {

			// HttpServletRequest request) {
			// Principal principal = request.getUserPrincipal();
			// return principal.getName();
			Customer existed =

					customerService.findByEmail(userDetails.getUsername())
							.orElseThrow(() -> new CustomerNotFoundException());

			existed.setAddress1(cust.getAddress2());

			if (!cust.getAddress2().isEmpty()) {
				existed.setAddress2(cust.getAddress2());
			}
			existed.setCity(cust.getCity());
			existed.setRegion(cust.getRegion());
			existed.setCountry(cust.getCountry());
			existed.setPostalCode(cust.getPostalCode());
			existed.setShippingRegionId(cust.getShippingRegionId());

			return ResponseEntity.ok(customerService.save(existed));

		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Token expired");
		}

	}

	/*
	 * API endpoint to update customers creditcard
	 */
	@PutMapping(path = "/api/customers/creditCard")
	public ResponseEntity<Customer> updateCustomerCreditCard(@Valid @RequestBody CustomerCreditCardForm cust,
			UserDetails userDetails) {
		try {

			Customer existed =

					customerService.findByEmail(userDetails.getUsername())
							.orElseThrow(() -> new CustomerNotFoundException());

			existed.setCreditCard(cust.getCreditCard());

			return ResponseEntity.ok(customerService.save(existed));

		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Token expired");
		}

	}
	
	

}
