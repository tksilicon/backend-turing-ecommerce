/**
 * 
 */
package com.turing.ecommerce.controllers;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.turing.ecommerce.DTO.AuthenticationRequest;
import com.turing.ecommerce.DTO.CustomerAddressForm;
import com.turing.ecommerce.DTO.CustomerCreditCardForm;
import com.turing.ecommerce.DTO.CustomerForm;
import com.turing.ecommerce.DTO.CustomerRegister;
import com.turing.ecommerce.DTO.CustomerUpdateForm;
import com.turing.ecommerce.exceptions.CustomerExistException;
import com.turing.ecommerce.exceptions.CustomerNotFoundException;
import com.turing.ecommerce.exceptions.FacebookException;
import com.turing.ecommerce.exceptions.error;
import com.turing.ecommerce.facebook.Facebook;
import com.turing.ecommerce.model.Customer;
import com.turing.ecommerce.repository.CustomerRepository;
import com.turing.ecommerce.security.jwt.JwtTokenProvider;
import com.turing.ecommerce.service.CustomCustomerDetailsService;
import com.turing.ecommerce.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Customer Controller for all Rest APIs endpoints related to Customers.
 * 
 * @author thankgodukachukwu
 *
 */
@Api(value = "Everything about Customers")
@RestController
@Slf4j
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

	public static final String TOKENEXPIRED = "Token expired";
	public static final String SCHEMASTRING = "schema";
	public static final String CUSTOMERSTRING = "customer";
	public static final String ACCESSTOKENSTRING = "access_token";
	public static final String EXPIREDSTRING = "expires_in";
	public static final String BEARERSTRING = "Bearer ";
	public static final String JSONTYPE = "application/json; charset=UTF-8";
	public static final String USERKEY = "USER-KEY";

	@Autowired
	CustomerRepository users;

	/*
	 * API endpoint to update customers
	 */
	@PutMapping(path = "/api/customer")
	public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody CustomerUpdateForm cust) {

		try {
			Customer existed =

					customerService.findByEmail(cust.getEmail())
							.orElseThrow(() -> new CustomerNotFoundException("(Update customer failed"));

			existed.setName(cust.getName());
			existed.setEmail(cust.getEmail());

			existed.setPassword(passwordEncoder.encode(cust.getPassword()));
			existed.setDayPhone(cust.getDayPhone());
			existed.setEvePhone(cust.getEvePhone());
			existed.setMobPhone(cust.getMobPhone());

			return ResponseEntity.ok(customerService.save(existed));

		} catch (AuthenticationException e) {
			throw new BadCredentialsException(TOKENEXPIRED);
		}

	}

	/**
	 * API endpoint to login customer
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */

	@PostMapping("/api/customers/login")
	public ResponseEntity<Map<String, Object>> signin(@RequestBody AuthenticationRequest data) {

		try {

			String username = data.getUsername();
			
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,
					data.getPassword(), AuthorityUtils.createAuthorityList("USER")));
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			String token = jwtTokenProvider.createToken(username,
					this.users.findByEmail(username)
							.orElseThrow(
									() -> new UsernameNotFoundException("The email +" + username + " doesn't exist"))
							.getRoles());


			Map<String, Object> mapResponse = new LinkedHashMap<>();

			Optional<Customer> cust = this.users.findByEmail(username);

			Map<String, Object> mapResponse2 = new LinkedHashMap<>();
			mapResponse2.put(SCHEMASTRING, cust);

			mapResponse.put(CUSTOMERSTRING, mapResponse2);
			mapResponse.put(ACCESSTOKENSTRING, token);
			mapResponse.put(EXPIREDSTRING, "24h");

			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_TYPE, JSONTYPE);
			headers.add(USERKEY, BEARERSTRING + token);

			return ResponseEntity.ok().headers(headers).body(mapResponse);

		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Email or Password is invalid");

		}
	}

	
	
	
	@RequestMapping("/")
    public ModelAndView redirectWithUsingRedirectPrefix(ModelMap model) {
        model.addAttribute("attribute", "/");
        return new ModelAndView("redirect:/swagger-ui.html#", model);
    }


	@SuppressWarnings("rawtypes")
	@GetMapping("/api/customer")
	public ResponseEntity currentUser(@AuthenticationPrincipal UserDetails userDetails) {
		Map<Object, Object> model = new HashMap<>();

		Optional<Customer> cust = this.users.findByEmail(userDetails.getUsername());
		model.put(CUSTOMERSTRING, cust);
		model.put("username", userDetails.getUsername());
		model.put("roles", userDetails.getAuthorities().stream().map(a -> ((GrantedAuthority) a).getAuthority())
				.collect(toList()));
		return ok(cust);
	}

	@ApiOperation(value = "Sign in with a facebook login token", response = Map.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Return a Object of Customer with auth credencials", response = CustomerRegister.class),
			@ApiResponse(code = 400, message = "Return a error object", response = error.class) })

	@PostMapping("/api/customers/facebook")
	public ResponseEntity<Map<String, Object>> home(
			@RequestParam(name = "access_token", required = true) String access_token) {

		Facebook fb = null;
		try {
			fb = new Facebook(access_token);

		} catch (Exception ex) {
			throw new FacebookException("Couldn't login to Facebook");
		}
		String username = fb.getProfile().getEmail();
		Customer existed =

				customerService.findByEmail(username)
						.orElseThrow(() -> new CustomerNotFoundException("(Email is not registered"));

		try {

			Authentication authentication = new UsernamePasswordAuthenticationToken(username, null,
					AuthorityUtils.createAuthorityList("USER"));
			SecurityContextHolder.getContext().setAuthentication(authentication);

			List<String> roles = new LinkedList<>();
			roles.add("USER");

			String token = jwtTokenProvider.createToken(username, roles);

			Map<String, Object> mapResponse = new LinkedHashMap<>();

			Map<String, Object> mapResponse2 = new LinkedHashMap<>();
			mapResponse2.put(SCHEMASTRING, existed);

			mapResponse.put(CUSTOMERSTRING, mapResponse2);

			mapResponse.put(ACCESSTOKENSTRING, BEARERSTRING + token);
			mapResponse.put(EXPIREDSTRING, "24h");

			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_TYPE, JSONTYPE);
			headers.add(USERKEY, token);

			return ResponseEntity.ok().headers(headers).body(mapResponse);

		} catch (Exception ex) {
			throw new FacebookException("Facebook Login didn't succeed");
		}

	}

	/*
	 * API endpoint to register customers
	 */
	@PostMapping(path = "/api/customers")
	public ResponseEntity registerCustomer(@Valid @RequestBody CustomerForm cust, final BindingResult bindingResult) {

		Optional<Customer> existed = customerService.findByEmail(cust.getEmail());

		if (existed.isPresent()) {
			throw new CustomerExistException("The email already exists.");
		}

		Customer existsx = new Customer();

		existsx.setName(cust.getName());
		existsx.setEmail(cust.getEmail());

		existsx.setPassword(passwordEncoder.encode(cust.getPassword()));
		

		customerService.save(existsx);

		Map<String, Object> mapResponse = new LinkedHashMap<>();

		Map<String, Object> mapResponse2 = new LinkedHashMap<>();
        mapResponse2.put(SCHEMASTRING, existsx);
		
		
		mapResponse.put(CUSTOMERSTRING, existsx);

		String token = jwtTokenProvider.createToken(cust.getEmail(), Arrays.asList("USER"));

		String username = cust.getEmail();
		

		Authentication authentication = new UsernamePasswordAuthenticationToken(username, cust.getPassword(),
				AuthorityUtils.createAuthorityList("USER"));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		mapResponse.put(ACCESSTOKENSTRING, BEARERSTRING + token);
		mapResponse.put(EXPIREDSTRING, "24h");

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE,JSONTYPE);
		headers.add(USERKEY, BEARERSTRING + token);

		return ResponseEntity.ok().headers(headers).body(mapResponse);

	}

	/*
	 * API endpoint to update customers address
	 */
	@PutMapping(path = "/api/customers/address")
	public ResponseEntity<Customer> updateCustomerAddress(@Valid @RequestBody CustomerAddressForm cust,
			@AuthenticationPrincipal UserDetails userDetails) {
		try {

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
			throw new BadCredentialsException(TOKENEXPIRED);
		}

	}

	/*
	 * API endpoint to update customers creditcard
	 */
	@PutMapping(path = "/api/customers/creditCard")
	public ResponseEntity<Customer> updateCustomerCreditCard(@Valid @RequestBody CustomerCreditCardForm cust,
			UserDetails userDetails) {
		try {

			Customer existed = customerService.findByEmail(userDetails.getUsername())
					.orElseThrow(() -> new CustomerNotFoundException());

			existed.setCreditCard(cust.getCreditCard());

			return ResponseEntity.ok(customerService.save(existed));

		} catch (AuthenticationException e) {
			throw new BadCredentialsException(TOKENEXPIRED);
		}

	}
	
	public Collection<GrantedAuthority> getAuthorities() {
        //make everyone ROLE_USER
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        GrantedAuthority grantedAuthority = new GrantedAuthority() {
            //anonymous inner type
            public String getAuthority() {
                return "ROLE_USER";
            }
        }; 
        grantedAuthorities.add(grantedAuthority);
        return grantedAuthorities;
    }

}
