package com.turing.ecommerce;

import static io.restassured.RestAssured.given;
import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turing.ecommerce.DTO.AuthenticationRequest;
import com.turing.ecommerce.DTO.CartWithProduct;
import com.turing.ecommerce.DTO.CustomerForm;
import com.turing.ecommerce.DTO.ShoppingCartForm;
import com.turing.ecommerce.model.Customer;
import com.turing.ecommerce.service.CartService;
import com.turing.ecommerce.service.CustomerService;
import com.turing.ecommerce.utils.CustomerJson;
import com.turing.ecommerce.utils.Uid;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class TuringApplicationTests {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	private String token;

	@LocalServerPort
	int randomServerPort;

	public static String emailAddress;
	public static String password;
	Map<String,Object> map;
	

	@Autowired
	ObjectMapper objectMapper;

	public static final String BEARERSTRING = "Bearer ";
	;

	@Test
	public void contextLoads() {
	}

	public String getEmail() {

		// Generate different email for testing
		String str = Uid.generateRandomId(7, "abcdefghjkmnpqrstuvwxyz23456789", Character.LOWERCASE_LETTER);
		emailAddress = str + "@gmail.com";
		return emailAddress;

	}

	public String getPassword() {
		return password = Uid.generateRandomId(7, "abcdefghjkmnpqrstuvwxyz23456789", Character.LOWERCASE_LETTER);
	}

	
    /**
	 * Test a login customer integration testing
	 * 
	 * @throws Exception
	 * @throws JsonProcessingException
	 * 
	 * @see {@link com.turing.ecommerce.controllers.CustomerController#signin(AuthenticationRequest}.
	 * 
	 * @throws Exception
	 */

    @Before
	public void setup() {

		this.getEmail();
		this.getPassword();
		

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		RestAssured.port = randomServerPort;
		

		token = given()
				.contentType(ContentType.JSON).body(AuthenticationRequest.builder()
						.username("jameswong@turing.com").password("james").build())
				.when().post("/api/customers/login").andReturn().jsonPath().getString("access_token");
		
		log.debug("Got token:" + token);

	}

	/**
	 * Test register a customer unit/integrated testing
	 * 
	 * @throws Exception
	 * 
	 * 
	 * @see {@link com.turing.ecommerce.controllers.CustomerController#registerCustomer(CustomerForm}.
	 * 
	 * @throws Exception
	 */

    @SuppressWarnings("unchecked")
	@Test
	public void testRegisterCustomer() throws Exception {

		
		// Given

		CustomerForm cust = new CustomerForm();
		cust.setEmail(TuringApplicationTests.emailAddress);
		cust.setName("ThankGod Ukachukwu");
		cust.setPassword("thankgod");

		Customer customer = new Customer();
		customer.setEmail(TuringApplicationTests.emailAddress);
		customer.setName("ThankGod Ukachukwu");
		customer.setPassword("thankgod");

		CustomerService mock = org.mockito.Mockito.mock(CustomerService.class);

		given(mock.findByEmail(cust.getEmail())).willReturn(Optional.of(customer));

		// when + then

		

		MvcResult result = this.mockMvc.perform(
				post("/api/customers").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(cust)))
				.andDo(print()).andExpect(status().isOk()).andReturn();// .andExpect(content().json(mapper.writeValueAsString(customer)));
		
		
		
		
	}
    
    /**
	 * Test a login customer integration testing
	 * 
	 * @throws Exception
	 * @throws JsonProcessingException
	 * 
	 * @see {@link com.turing.ecommerce.controllers.CustomerController#signin(AuthenticationRequest}.
	 * 
	 * @throws Exception
	 */
    
  


	/**
	 * Test a get customer unit testing
	 * 
	 * @throws Exception
	 * @throws JsonProcessingException
	 * 
	 * @see {@link com.turing.ecommerce.controllers.CustomerController#getCustomerById(Authentication)}.
	 * 
	 * @throws Exception
	 */

	@Test
	public void testTCustomer() throws Exception {
	
		// @formatter:off
		given().header("USER-KEY", BEARERSTRING + token)

				.accept(ContentType.JSON)

				.when().get("/api/customer")

				.then().assertThat().statusCode(200);
		// @formatter:on
	}

	

}
