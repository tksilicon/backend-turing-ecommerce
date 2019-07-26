package com.turing.ecommerce.controllers;

import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.model.Charge;
import com.turing.ecommerce.DTO.AttributeDTO;
import com.turing.ecommerce.DTO.AttributesProductDTO;
import com.turing.ecommerce.DTO.AuthenticationRequest;
import com.turing.ecommerce.DTO.CategoryAllDTO;
import com.turing.ecommerce.DTO.CategoryDTO;
import com.turing.ecommerce.DTO.CategoryProductDTO;
import com.turing.ecommerce.DTO.ChargeRequest;
import com.turing.ecommerce.DTO.ChargeRequest.Currency;
import com.turing.ecommerce.DTO.CustomerForm;
import com.turing.ecommerce.DTO.CustomerUpdateForm;
import com.turing.ecommerce.DTO.DepartmentDTO;
import com.turing.ecommerce.DTO.ProductCategoryDTO;
import com.turing.ecommerce.DTO.ProductDetailsDTO;
import com.turing.ecommerce.DTO.ProductLocationsDTO;
import com.turing.ecommerce.DTO.ProductReviewDTO;
import com.turing.ecommerce.DTO.ReviewDTO;
import com.turing.ecommerce.DTO.ShoppingCartForm;
import com.turing.ecommerce.DTO.ShoppingCartProduct;
import com.turing.ecommerce.model.Customer;
import com.turing.ecommerce.model.Product;
import com.turing.ecommerce.model.ShoppingCart;
import com.turing.ecommerce.service.AttributeDAOService;
import com.turing.ecommerce.service.AttributesService;
import com.turing.ecommerce.service.CartService;
import com.turing.ecommerce.service.CategoryService;
import com.turing.ecommerce.service.CustomerService;
import com.turing.ecommerce.service.DepartmentService;
import com.turing.ecommerce.service.ProdCatDAOService;
import com.turing.ecommerce.service.ProductService;
import com.turing.ecommerce.service.StripeService;
import com.turing.ecommerce.utils.Uid;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * 
 *
 * @author thankgodukachukwu
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTestClass {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@LocalServerPort
	int port;

	@Autowired
	ObjectMapper objectMapper;

	private String token;

	private String emailAddress;
	private String password;

	public String getEmail() {
		// Generate different email for testing
		String str = Uid.generateRandomId(7, "abcdefghjkmnpqrstuvwxyz23456789", Character.LOWERCASE_LETTER);
		emailAddress = str + "@gmail.com";
		return emailAddress;

	}

	public String getPassword() {
		return password = Uid.generateRandomId(7, "abcdefghjkmnpqrstuvwxyz23456789", Character.LOWERCASE_LETTER);
	}

	@Before
	public void setup() {

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

		this.getEmail();
		this.getPassword();

		RestAssured.port = 8080;
		token = given().contentType(ContentType.JSON)
				.body(AuthenticationRequest.builder().username(emailAddress).password(password).build()).when()
				.post("/api/customers/login").andReturn().jsonPath().getString("token");
		log.debug("Got token:" + token);

	}

	/**
	 * Test Get a department Unit testing of controller using Mockito
	 * 
	 * @throws Exception
	 * 
	 * 
	 **/

	@Test
	@Transactional
	public void testGetDepartment() throws Exception {

		// given

		DepartmentDTO department = new DepartmentDTO();

		department.setDepartmentId(1);

		department.setName("Africa");

		Optional<DepartmentDTO> optionalDepartment = Optional.of(department);
		DepartmentService mock = org.mockito.Mockito.mock(DepartmentService.class);

		given(mock.findById(department.getDepartmentId())).willReturn(optionalDepartment);

		// Get Department

		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/api/departments/{department_id}", 1)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.departmentId").value(1));

	}

	/**
	 * 
	 * 
	 * Tests get all departments Unit testing of controller using Mockito
	 * 
	 * @throws Exception
	 * 
	 */

	@Test
	@Transactional
	public void testGetDepartments() throws Exception {

		// given

		DepartmentDTO department = new DepartmentDTO();

		department.setDepartmentId(1);
		department.setDescription("our department");

		department.setName("Africa");

		List<DepartmentDTO> departments = Arrays.asList(department);

		DepartmentService mock = org.mockito.Mockito.mock(DepartmentService.class);

		given(mock.getAll()).willReturn(departments);

		// when + then

		this.mockMvc.perform(get("/api/departments")).andExpect(status().isOk());

	}

	/**
	 * 
	 * 
	 * Tests get all category Unit testing of category controller using Mockito
	 * 
	 * See
	 * {@linkcom.turing.ecommerce.controllers.CategoryController#getCategoryByProductId(Integer)}.
	 * 
	 * 
	 * 
	 * @throws Exception
	 * 
	 */

	@Test
	@Transactional
	public void testGetCategory() throws Exception {

		// given

		CategoryAllDTO category = new CategoryAllDTO();

		category.setCategoryId(1);
		category.setDepartmentId(1);

		category.setName("Africa");

		List<CategoryAllDTO> categories = Arrays.asList(category);

		CategoryService mock = org.mockito.Mockito.mock(CategoryService.class);

		given(mock.getAll()).willReturn(categories);

		// when + then

		this.mockMvc.perform(get("/api/categories")).andExpect(status().isOk());
	}

	/**
	 * 
	 * 
	 * 
	 * Unit testing of category controller using Mockito
	 * 
	 * See
	 * {@linkcom.turing.ecommerce.controllers.CategoryController#getCategoryByDepartmentId(Integer)}.
	 * 
	 * 
	 * 
	 * @throws Exception
	 * 
	 */

	@Test
	@Transactional
	public void testGetCategoryByDepartmentId() throws Exception {

		// given

		CategoryDTO category = new CategoryDTO(4, 2,
				"Our ever-growing selection of beautiful animal T-shirts represents critters from everywhere, "
						+ "both wild and domestic. If you don't see the T-shirt with the animal you're looking for, tell us and we'll find it!");

		CategoryDTO category2 = new CategoryDTO(5, 2, "Flower", "These unique and beautiful flower T-shirts are"
				+ " just the item for the gardener, flower arranger, florist, or general lover of things beautiful. "
				+ "Surprise the flower in your life with one of the beautiful botanical T-shirts or just get a few for yourself!");

		List<CategoryDTO> categories = Arrays.asList(category, category2);

		CategoryService mock = org.mockito.Mockito.mock(CategoryService.class);
		given(mock.getCategoryByDepartmentId(2)).willReturn(categories);

		// when + then

		this.mockMvc.perform(get("/api/categories/inDepartment/{department_id}", 2)).andExpect(status().isOk());

	}

	/**
	 * 
	 * 
	 * 
	 * Unit testing of category controller using Mockito
	 * 
	 * See
	 * {@linkcom.turing.ecommerce.controllers.CategoryController#getCategoryByProductId(Integer)}.
	 * 
	 * 
	 * 
	 * @throws Exception
	 * 
	 */

	@Test
	@Transactional
	public void testGetCategoryByProductId() throws Exception {

		// given
		CategoryProductDTO cattt = new CategoryProductDTO();
		cattt.setCategoryId(1);
		cattt.setDepartmentId(1);
		cattt.setName("French");

		ProdCatDAOService mock = org.mockito.Mockito.mock(ProdCatDAOService.class);

		given(mock.findByProductId(18)).willReturn(cattt);

		// when + then

		mockMvc.perform(

				MockMvcRequestBuilders.get("/api/categories/inProduct/{product_id}", 18)
						.accept(MediaType.APPLICATION_JSON))

				.andDo(print()).andExpect(status().isOk())

				.andExpect(MockMvcResultMatchers.jsonPath("$.categoryId").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("French"))
				.andDo(MockMvcResultHandlers.print());

	}

	/**
	 * Test Get a attribute Unit testing of controller using Mockito
	 * 
	 * @throws Exception
	 * 
	 * 
	 */
	@Test
	@Transactional
	public void testGetAttribute() throws Exception {

		// given

		AttributeDTO attribute = new AttributeDTO();

		attribute.setAttributeId(1);

		attribute.setName("Size");

		Optional<AttributeDTO> optionalAttribute = Optional.of(attribute);

		AttributesService mock = org.mockito.Mockito.mock(AttributesService.class);

		given(mock.findById(attribute.getAttributeId())).willReturn(optionalAttribute);

		// Get Department

		mockMvc.perform(

				MockMvcRequestBuilders.get("/api/attributes/{attribute_id}", 1).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.attributeId").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Size"));

	}

	/**
	 * 
	 * 
	 * Tests get all attributes Unit testing of controller using Mockito
	 * 
	 * @throws Exception
	 * 
	 */

	@Test
	@Transactional
	public void testGetAttributess() throws Exception {

		// given

		AttributeDTO attribute = new AttributeDTO();

		attribute.setAttributeId(1);

		attribute.setName("Size");

		List<AttributeDTO> attributes = Arrays.asList(attribute);

		AttributesService mock = org.mockito.Mockito.mock(AttributesService.class);

		given(mock.getAll()).willReturn(attributes);

		// when + then

		this.mockMvc.perform(get("/api/attributes")).andExpect(status().isOk()).andDo(print())
				.andExpect(status().isOk());
	}

	/**
	 * 
	 * 
	 * 
	 * Unit testing of Attributes controller using Mockito
	 * 
	 * See
	 * {@linkcom.turing.ecommerce.controllers.AttributesController#getValuesByAttributeId(Integer)}.
	 * 
	 * 
	 * 
	 * @throws Exception
	 * 
	 */

	@Test
	@Transactional
	public void testGetValuesByAttributeId() throws Exception {

		// given

		AttributeDTO attribute = new AttributeDTO(1, "S");
		AttributeDTO attribute2 = new AttributeDTO(2, "M");

		AttributeDTO attribute3 = new AttributeDTO(3, "L");
		AttributeDTO attribute4 = new AttributeDTO(4, "XL");
		AttributeDTO attribute5 = new AttributeDTO(5, "XXL");

		List<AttributeDTO> attribues = Arrays.asList(attribute, attribute2, attribute3, attribute4, attribute5);
		AttributesService mock = org.mockito.Mockito.mock(AttributesService.class);
		given(mock.getValuesByAttributeId(1)).willReturn(attribues);

		// when + then

		this.mockMvc.perform(get("/api/attributes/values/{attribute_id}", 1)).andExpect(status().isOk()).andDo(print())
				.andExpect(content().json(
						"[{'attributeId': 1, 'name': 'S'},{'attributeId': 2, 'name': 'M'}, {'attributeId': 3, 'name': 'L'},"
								+ " {'attributeId': 4, 'name': 'XL'},{'attributeId': 5, 'name': 'XXL'}]"));

	}

	/**
	 * 
	 * 
	 * 
	 * Unit testing of Attribute controller using Mockito
	 * 
	 * See
	 * {@linkcom.turing.ecommerce.controllers.AttributeController#getAttributeValuesByProductId(Integer)}.
	 * 
	 * 
	 * 
	 * @throws Exception
	 * 
	 */

	@Test
	@Transactional
	public void testGetAttributeValuesByProductId() throws Exception {

		// given
		AttributesProductDTO aattt = new AttributesProductDTO();
		aattt.setName("Color");
		aattt.setAttributeValueId(6);
		aattt.setValue("White");

		List<AttributesProductDTO> attribues = Arrays.asList(aattt);

		AttributeDAOService mock = org.mockito.Mockito.mock(AttributeDAOService.class);

		given(mock.findAttributesByProductId(1)).willReturn(attribues);

		// when + then

		mockMvc.perform(

				MockMvcRequestBuilders.get("/api/attributes/inProduct/{product_id}", 1)
						.accept(MediaType.APPLICATION_JSON))

				.andDo(print()).andExpect(status().isOk())

				.andDo(MockMvcResultHandlers.print())

				.andExpect(content().json(
						"[{'name': 'Size', 'attributeValueId': 1,'value': 'S' }, {'name': 'Size', 'attributeValueId': 2,'value': 'M' },"
								+ "{'name': 'Size', 'attributeValueId': 3,'value': 'L' }, {'name': 'Size', 'attributeValueId': 4,'value': 'XL' },"
								+ "{'name': 'Size', 'attributeValueId': 5,'value': 'XXL' }, {'name': 'Color', 'attributeValueId': 6,'value': 'White' },"
								+ "{'name': 'Color', 'attributeValueId': 7,'value': 'Black' }, {'name': 'Color', 'attributeValueId': 8,'value': 'Red' },"
								+ "{'name': 'Color', 'attributeValueId': 9,'value': 'Orange' }, {'name': 'Color', 'attributeValueId': 10,'value': 'Yellow' },"
								+ "{'name': 'Color', 'attributeValueId': 11,'value': 'Green' }, {'name': 'Color', 'attributeValueId': 12,'value': 'Blue' },"
								+ "{'name': 'Color', 'attributeValueId': 13,'value': 'Indigo' }, {'name': 'Color', 'attributeValueId': 14,'value': 'Purple' }]"));

	}

	/**
	 * 
	 * 
	 * Tests get all Produc Unit testing of Product controller using Mockito Testing
	 * 
	 * 
	 * See {@link com.turing.ecommerce.controllers.ProductController#getAll()}.
	 * 
	 * 
	 * @throws Exception
	 * 
	 */

	@Test
	@Transactional
	public void testGetProducts() throws Exception {

		// given
		Product product = new Product();
		product.setProductId(1);
		product.setName("Arc d\'Triomphe");
		product.setDescription("This beautiful and iconic T-shirt will no doubt lead you to your own triumph.");

		List<Product> products = Arrays.asList(product);

		Map<String, Object> mapOfReturn = new HashMap<String, Object>();

		mapOfReturn.put("count", products);
		mapOfReturn.put("rows", products.size());

		ProductService mock = org.mockito.Mockito.mock(ProductService.class);

		given(mock.getAll(1, 1, null)).willReturn(mapOfReturn);

		// when + then

		ResultActions result = mockMvc.perform(

				MockMvcRequestBuilders.get("/api/products?page=1&limit=").accept(MediaType.APPLICATION_JSON))

				.andDo(print()).andExpect(status().isBadRequest())

				.andDo(MockMvcResultHandlers.print());

		// String content = result.andReturn().getResponse().getContentAsString();

		// String expected = "{\"count\":1,
		// \"rows\":[{\"productId\":1,\"description\":\"This beautiful and iconic
		// T-shirt will no doubt lead you to your own triumph.\","
		// + "
		// \"discountedPrice\":0.00,\"display\":0,\"image\":\"arc-d-triomphe.gif\",\"image2\":\"arc-d-triomphe-2.gif\","
		// + " \"name\":\"Arc
		// d'Triomphe\",\"price\":14.99,\"thumbnail\":\"arc-d-triomphe-thumbnail.gif\"}]}";

		// JSONObject expectedJSon = new JSONObject(content);
		// JSONObject actualJSon = new JSONObject(expected);

		// assertEquals(expectedJSon.toString(), actualJSon.toString());

	}

	/**
	 * 
	 * Tests get all Product Unit testing of Product controller using Mockito
	 * Testing
	 * 
	 * 
	 * See
	 * {@link com.turing.ecommerce.service.ProductServiceImpl#productSearchQueryAllWords(String, String, Integer, Integer, Integer)}.
	 * 
	 * 
	 * @throws Exception
	 * 
	 */

	@Test
	@Transactional
	public void testGetProductSearch() throws Exception {

		// given
		Product product = new Product();
		product.setProductId(1);
		product.setName("Arc d\'Triomphe");
		product.setDescription("This beautiful and iconic T-shirt will no doubt lead you to your own triumph.");

		List<Product> products = Arrays.asList(product);

		Map<String, Object> mapOfReturn = new HashMap<String, Object>();

		mapOfReturn.put("count", products);
		mapOfReturn.put("rows", products.size());

		ProductService mock = org.mockito.Mockito.mock(ProductService.class);

		given(mock.productSearch("Arc", null, 1, 1, null)).willReturn(mapOfReturn);

		// when + then

		ResultActions result = mockMvc.perform(

				MockMvcRequestBuilders.get("/api/products/search?page=1&limit=1&query_string=Arc")
						.accept(MediaType.APPLICATION_JSON))

				.andDo(print()).andExpect(status().isOk())

				.andDo(MockMvcResultHandlers.print());

		String content = result.andReturn().getResponse().getContentAsString();

		String expected = "{\"count\":1, \"rows\":[{\"productId\":1,\"description\":\"This beautiful and iconic T-shirt will no doubt lead you to your own triumph.\","
				+ " \"discountedPrice\":0.00,\"display\":0,\"image\":\"arc-d-triomphe.gif\",\"image2\":\"arc-d-triomphe-2.gif\","
				+ " \"name\":\"Arc d'Triomphe\",\"price\":14.99,\"thumbnail\":\"arc-d-triomphe-thumbnail.gif\"}]}";

		JSONObject jsonObj = new JSONObject(content);
		JSONObject jsonObj2 = new JSONObject(expected);

		assertEquals(jsonObj2.toString(), jsonObj.toString());

	}

	/**
	 * Test Get a product Unit testing of controller using Mockito
	 * 
	 * @throws Exception
	 * 
	 **/

	@Test
	@Transactional
	public void testGetProduct() throws Exception {

		// given
		Product product = new Product();
		product.setProductId(1);
		product.setName("Arc d\'Triomphe");
		product.setDescription("This beautiful and iconic T-shirt will no doubt lead you to your own triumph.");

		Optional<Product> optionalProduct = Optional.of(product);
		ProductService mock = org.mockito.Mockito.mock(ProductService.class);

		given(mock.findById(product.getProductId())).willReturn(optionalProduct);

		// Get Department

		mockMvc.perform(

				MockMvcRequestBuilders.get("/api/products/{product_id}", 1).accept(MediaType.APPLICATION_JSON))

				.andDo(print()).andExpect(status().isOk())

				.andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(1));

	}

	/**
	 * 
	 * Tests get all Produc Unit testing of Product controller using Mockito Testing
	 * 
	 * 
	 * See
	 * {@link com.turing.ecommerce.controllers.ProductController#productOfCategories(Integer, Integer, Integer, Integer)}.
	 * 
	 * 
	 * @throws Exception
	 */

	@Test
	@Transactional
	public void testGetProductsCategory() throws Exception {

		// given
		ProductCategoryDTO product = new ProductCategoryDTO();
		product.setProductId(1);
		product.setName("Arc d\'Triomphe");
		product.setDescription("This beautiful and iconic T-shirt will no doubt lead you to your own triumph.");

		List<ProductCategoryDTO> products = Arrays.asList(product);

		Map<String, Object> mapOfReturn = new HashMap<String, Object>();

		mapOfReturn.put("count", products);
		mapOfReturn.put("rows", products.size());

		ProductService mock = org.mockito.Mockito.mock(ProductService.class);

		given(mock.productCategorySearch(1, 1, 1, null)).willReturn(mapOfReturn);

		// when + then

		ResultActions result = mockMvc.perform(

				MockMvcRequestBuilders.get("/api/products/inCategory/{category_id}?page=1&limit=1", 1)
						.accept(MediaType.APPLICATION_JSON))

				.andDo(print()).andExpect(status().isOk())

				.andDo(MockMvcResultHandlers.print());

		String content = result.andReturn().getResponse().getContentAsString();

		String expected = "{\"count\":1, \"rows\":[{ \"name\":\"Arc d'Triomphe\", \"productId\":1,"
				+ "\"description\":\"This beautiful and iconic T-shirt will no doubt lead you to your own triumph.\","
				+ " \"price\":14.99,\"discountedPrice\":0.00," + " \"thumbnail\":\"arc-d-triomphe-thumbnail.gif\"}]}";

		JSONObject expectedJSon = new JSONObject(content);
		JSONObject actualJSon = new JSONObject(expected);

		assertEquals(expectedJSon.toString(), actualJSon.toString());

	}

	/**
	 * 
	 * Tests get all Produc Unit testing of Product controller using Mockito Testing
	 * 
	 * 
	 * See
	 * {@link com.turing.ecommerce.controllers.ProductController#productOfDepartment(Integer, Integer, Integer, Integer)}.
	 * 
	 * 
	 * @throws Exception
	 **/

	@Test
	@Transactional
	public void testGetProductOfDepartments() throws Exception {

		// given
		ProductCategoryDTO product = new ProductCategoryDTO();
		product.setProductId(1);
		product.setName("Arc d\'Triomphe");
		product.setDescription("This beautiful and iconic T-shirt will no doubt lead you to your own triumph.");

		List<ProductCategoryDTO> products = Arrays.asList(product);

		Map<String, Object> mapOfReturn = new HashMap<String, Object>();

		mapOfReturn.put("count", products);
		mapOfReturn.put("rows", products.size());

		ProductService mock = org.mockito.Mockito.mock(ProductService.class);

		given(mock.productDepartmentSearch(1, 1, 1, null)).willReturn(mapOfReturn);

		// when + then

		ResultActions result = mockMvc.perform(

				MockMvcRequestBuilders.get("/api/products/inDepartment/{department_id}?page=1&limit=1", 1)
						.accept(MediaType.APPLICATION_JSON))

				.andDo(print()).andExpect(status().isOk())

				.andDo(MockMvcResultHandlers.print());

		String content = result.andReturn().getResponse().getContentAsString();

		String expected = "{\"count\":1, \"rows\":[{ \"name\":\"Arc d'Triomphe\", \"productId\":1,"
				+ "\"description\":\"This beautiful and iconic T-shirt will no doubt lead you to your own triumph.\","
				+ " \"price\":14.99,\"discountedPrice\":0.00," + " \"thumbnail\":\"arc-d-triomphe-thumbnail.gif\"}]}";

		JSONObject expectedJSon = new JSONObject(content);
		JSONObject actualJSon = new JSONObject(expected);

		assertEquals(expectedJSon.toString(), actualJSon.toString());

	}

	/**
	 * Test Get productdetails Unit testing of controller using Mockito
	 * 
	 * @throws Exception
	 * 
	 * 
	 **/

	@Test
	@Transactional
	public void testGetProductDetails() throws Exception {

		// given
		ProductDetailsDTO product = new ProductDetailsDTO();
		product.setProductId(1);
		product.setName("Arc d\'Triomphe");
		product.setDescription("This beautiful and iconic T-shirt will no doubt lead you to your own triumph.");

		Optional<ProductDetailsDTO> optionalProduct = Optional.of(product);

		ProductService mock = org.mockito.Mockito.mock(ProductService.class);

		given(mock.getProductDetails(product.getProductId())).willReturn(optionalProduct);

		// Get Product Details

		mockMvc.perform(

				MockMvcRequestBuilders.get("/api/products/{product_id}/details", 1).accept(MediaType.APPLICATION_JSON))

				.andDo(print()).andExpect(status().isOk())

				.andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(1));

	}

	/**
	 * Test Get productdetails Unit testing of controller using Mockito
	 * 
	 * @throws Exception
	 * 
	 * 
	 **/

	@Test
	@Transactional
	public void testGetProductLocations() throws Exception {

		// given
		ProductLocationsDTO product = new ProductLocationsDTO();
		product.setCategoryId(1);
		product.setCategoryName("French");
		product.setDepartmentId(1);
		product.setDepartmentName("Regional");

		Optional<ProductLocationsDTO> optionalProduct = Optional.of(product);

		ProductService mock = org.mockito.Mockito.mock(ProductService.class);

		given(mock.getProductLocations(1)).willReturn(optionalProduct);

		// Get Product Locations

		mockMvc.perform(

				MockMvcRequestBuilders.get("/api/products/{product_id}/locations", 1)
						.accept(MediaType.APPLICATION_JSON))

				.andDo(print()).andExpect(status().isOk())

				.andExpect(MockMvcResultMatchers.jsonPath("$.categoryId").value(1));

	}

	/**
	 * Test Get productReviews Unit testing of controller using Mockito
	 * 
	 * @throws Exception
	 * 
	 * 
	 **/

	@Test
	@Transactional
	public void testGetProductReviews() throws Exception {

		// given
		ReviewDTO product = new ReviewDTO();
		product.setName("Eder Taveira");
		;
		product.setReview("That\'s a good product. The best for me.");
		short s = 5;
		product.setRating(s);
		;

		String dateString = "2019-02-17 13:57:29";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		product.setCreatedOn(formatter.parse(dateString));

		List<ReviewDTO> listOfReviews = Arrays.asList(product);

		ProductService mock = org.mockito.Mockito.mock(ProductService.class);
		given(mock.getProductReviews(1)).willReturn(listOfReviews);

		// Get Product Locations

		mockMvc.perform(

				MockMvcRequestBuilders.get("/api/products/{product_id}/reviews", 1).accept(MediaType.APPLICATION_JSON))

				.andDo(print()).andExpect(status().isOk());

	}

	/**
	 * Test a post review unit testing
	 * 
	 * @throws Exception
	 * @throws JsonProcessingException
	 * 
	 * 
	 * @throws MethodArgumentNotValidException
	 */

	@Test
	@Transactional
	public void testPostReview() throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();

		// Given
		ProductReviewDTO product = new ProductReviewDTO();

		product.setReview("That\'s a good product. The best for me.");
		short s = 1;
		product.setRating(s);
		;

		// when + then
		mockMvc.perform(post("/api/products/{product_id}/reviews?review=gjsghdg&rating=yyuuu", 1)
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(product))).andDo(print())
				.andExpect(status().isOk());

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

	@Test
	@Transactional
	public void testRegisterCustomer() throws Exception {

		// Given

		this.getEmail();
		this.getPassword();

		CustomerForm cust = new CustomerForm();
		cust.setEmail(this.emailAddress);
		cust.setName("ThankGod Ukachukwu");
		cust.setPassword(this.password);

		Customer customer = new Customer();
		customer.setEmail(this.emailAddress);
		customer.setName(this.password);
		customer.setPassword("somtogugu101");

		CustomerService mock = org.mockito.Mockito.mock(CustomerService.class);

		given(mock.findByEmail(cust.getEmail())).willReturn(Optional.of(customer));

		// when + then

		ObjectMapper mapper = new ObjectMapper();

		this.mockMvc.perform(
				post("/api/customers").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(cust)))
				.andDo(print()).andExpect(status().isOk());// .andExpect(content().json(mapper.writeValueAsString(customer)));

	}

	/**
	 * Test a post customer unit testing
	 * 
	 * @throws Exception
	 * @throws JsonProcessingException
	 * 
	 * @see {@link com.turing.ecommerce.controllers.ProductController#productOfDepartment(CustomerForm)}.
	 * 
	 * @throws MethodArgumentNotValidException
	 */

	@Test
	@Transactional
	public void testUpdateCustomer() throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();

		RestAssured.port = 8080;
		token = given().contentType(ContentType.JSON)
				.body(AuthenticationRequest.builder().username("testingme2@yahoo.com").password("passme").build())
				.when().post("/api/customers/login").andReturn().jsonPath().getString("token");
		log.debug("Got token:" + token);

		CustomerUpdateForm cust = new CustomerUpdateForm();

		cust.setEmail("testingme2@yahoo.com");
		cust.setName("ThankGod Ukachukwu");
		cust.setPassword("passme");
		cust.setDayPhone("+351323213511235");
		cust.setEvePhone("+452436143246123");
		cust.setMobPhone("+351323213511235");

		// when + then
		mockMvc.perform(
				put("/api/customer").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(cust)))
				.andDo(print()).andExpect(status().isOk());

	}

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
	public void getCustomer() throws Exception {
		// @formatter:off
		given()

				.accept(ContentType.JSON)

				.when().get("http://localhost:" + port + "/api/customer")

				.then().assertThat().statusCode(200);
		// @formatter:on
	}

	/**
	 * Test add to cart unit/integrated testing
	 * 
	 * @throws Exception
	 * 
	 * 
	 * @see {@link com.turing.ecommerce.controllers.CustomerController#addToShoppingCart(ShoppingCartForm}.
	 * 
	 * @throws Exception
	 */

	private String carttId = null;

	@Test
	@Transactional
	public void testAddToCart() throws Exception {

		// Generate different email for testing
		this.carttId = Uid.generateRandomId(7, "abcdefghjkmnpqrstuvwxyz23456789", Character.LOWERCASE_LETTER);
		// Given

		this.getEmail();
		this.getPassword();

		ShoppingCartForm cartItem = new ShoppingCartForm();
		cartItem.setCartId(this.carttId);
		cartItem.setAttributes("LG, red");
		cartItem.setProductId(2);

		ShoppingCartProduct scp = new ShoppingCartProduct();
		scp.setAttributes("LG, red");
		scp.setProductId(2);

		CartService mock = org.mockito.Mockito.mock(CartService.class);

		List<ShoppingCartProduct> list = Arrays.asList(scp);

		given(mock.getShoppingCartProducts(cartItem)).willReturn(list);

		// when + then

		ObjectMapper mapper = new ObjectMapper();

		this.mockMvc.perform(post("/api/shoppingcart/add").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(cartItem))).andDo(print()).andExpect(status().isOk());// .andExpect(content().json(mapper.writeValueAsString(customer)));

	}

	/**
	 * Test add to cart unit/integrated testing
	 * 
	 * @throws Exception
	 * 
	 * 
	 * @see {@link com.turing.ecommerce.controllers.CustomerController#addToShoppingCart(ShoppingCartForm}.
	 * 
	 * @throws Exception
	 */

	@Test
	@Transactional
	public void testStripeCharge() throws Exception {

		ChargeRequest charge = new ChargeRequest();

		charge.setAmount(5);
		charge.setCurrency(Currency.USD);
		charge.setDescription("testing payment endpoint");
		charge.setOrderId(1);
		charge.setStripeToken("sk_test_vvpKl3yIpBVeoRJ3qxS6mjIF00jfi6Bi6j");

		StripeService mock = org.mockito.Mockito.mock(StripeService.class);

		given(mock.charge(charge)).willReturn(new Charge());

		// when + then

		ObjectMapper mapper = new ObjectMapper();

		this.mockMvc.perform(post("/api/stripe/charge").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(charge))).andDo(print()).andExpect(status().isOk());// .andExpect(content().json(mapper.writeValueAsString(customer)));

	}

}
