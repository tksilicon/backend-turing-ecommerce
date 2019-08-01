/**
 * 
 */
package com.turing.ecommerce.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;

import com.google.common.collect.Lists;
import com.turing.ecommerce.DTO.AttributeDTO;
import com.turing.ecommerce.DTO.AttributesProductDTO;
import com.turing.ecommerce.DTO.AuthenticationRequest;
import com.turing.ecommerce.DTO.CategoryAllDTO;
import com.turing.ecommerce.DTO.CategoryDTO;
import com.turing.ecommerce.DTO.CategoryBasic;
import com.turing.ecommerce.DTO.ChargeRequest;
import com.turing.ecommerce.DTO.CustomerAddressForm;
import com.turing.ecommerce.DTO.CustomerCreditCardForm;
import com.turing.ecommerce.DTO.CustomerForm;
import com.turing.ecommerce.DTO.CustomerOrderDTO;
import com.turing.ecommerce.DTO.CustomerRegister;
import com.turing.ecommerce.DTO.CustomerUpdateForm;
import com.turing.ecommerce.DTO.DepartmentDTO;
import com.turing.ecommerce.DTO.ItemForm;
import com.turing.ecommerce.DTO.NotFound;
import com.turing.ecommerce.DTO.OrderDTO;
import com.turing.ecommerce.DTO.OrderForm;
import com.turing.ecommerce.DTO.ProductInDepartment;
import com.turing.ecommerce.DTO.ProductDetail;
import com.turing.ecommerce.DTO.ProductLocations;
import com.turing.ecommerce.DTO.ProductReviewDTO;
import com.turing.ecommerce.DTO.ReviewDTO;
import com.turing.ecommerce.DTO.SavedItem;
import com.turing.ecommerce.DTO.ShippingRegionDTO;
import com.turing.ecommerce.DTO.ShoppingCartForm;
import com.turing.ecommerce.DTO.ShoppingCartProd;
import com.turing.ecommerce.DTO.CartWithProduct;
import com.turing.ecommerce.DTO.Unauthorized;
import com.turing.ecommerce.exceptions.error;
import com.turing.ecommerce.model.Review;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.VendorExtension;
import com.fasterxml.classmate.TypeResolver;

/**
 * @author thankgodukachukwu
 *
 */

@Configuration
@EnableSwagger2

public class SwaggerConfig {

	public static final String AUTHORIZATION_HEADER = "USER_KEY";
	public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";

	public static final String PRODUCT_ID = "/api/products/\\{product_id\\}/reviews";
	public static final String CUSTOMER = "/api/customer";
	public static final String ADDRESS = "/api/customers/address";
	public static final String CREDIT = "/api/customers/creditCard";
	public static final String ORDERS = "/api/orders";
	public static final String ORDERS_ID = "/api/orders/\\{order_id\\}";
	public static final String INCUSTOMER = "/api/orders/inCustomer";
	public static final String SHORTDETAIL = "/api/orders/shortDetail/\\{order_id\\}";
	

	@Bean
	public Docket apiDocket() {

		Contact contact = new Contact("ThankGod Ukachukwu", "https://github.com/tksilicon/backend-turing-ecommerce",
				"frankgod02@hotmail.com");
		
		

		List<VendorExtension> vendorExtensions = new ArrayList<>();

		ApiInfo apiInfo = new ApiInfo("Turing Ecommerce RESTful API documentation",
				"This pages documents Turing Ecommerce RESTful API endpoints", "1.0",
				"https://github.com/tksilicon/backend-turing-ecommerce", contact, "Apache 2.0",
				"http://www.apache.org/licenses/LICENSE-2.0", vendorExtensions);
		
		Set<String> protocols = new HashSet<String>();
		protocols.add("https");
		
		

		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.protocols(protocols)
				.apiInfo(apiInfo)
				
				.forCodeGeneration(true)
				

				//.ignoredParameterTypes(Pageable.class)
				//.ignoredParameterTypes(Optional.class)
				//.ignoredParameterTypes(ResponseEntity.class)
				
				//.ignoredParameterTypes( SavedItem.class, AttributeDTO.class, AttributesProductDTO.class,
						//AuthenticationRequest.class, CategoryAllDTO.class, CategoryDTO.class, CategoryAllDTO.class,
						 //ChargeRequest.class, CustomerAddressForm.class,
						//CustomerCreditCardForm.class, CustomerForm.class, CustomerOrderDTO.class,
						//CustomerUpdateForm.class, DepartmentDTO.class, ItemForm.class, OrderDTO.class, OrderForm.class,
						
						//ProductReviewDTO.class, ShippingRegionDTO.class, ShoppingCartForm.class, ShoppingCartProd.class)
				//.ignoredParameterTypes(java.sql.Date.class)
				//.directModelSubstitute(ReviewDTO.class, Review.class)
				.directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
				.directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
				.directModelSubstitute(java.time.LocalDateTime.class, Date.class).select()

				.apis(RequestHandlerSelectors.basePackage("com.turing.ecommerce.controllers"))

				.paths(PathSelectors.any()).build()
				.additionalModels(typeResolver.resolve(Unauthorized.class))
				.additionalModels(typeResolver.resolve(NotFound.class)) 
				.additionalModels(typeResolver.resolve(error.class)) 
				.additionalModels(typeResolver.resolve(CustomerRegister.class)) 
				

				.securityContexts(securityContext()).securitySchemes(Lists.newArrayList(apiKey()))
				.useDefaultResponseMessages(false);

		docket = docket.select().paths(regex(DEFAULT_INCLUDE_PATTERN)).build();

		return docket;

	}

	@Autowired
	private TypeResolver typeResolver;

	private ApiKey apiKey() {
		return new ApiKey("UserSecurity", AUTHORIZATION_HEADER, "header");
	}

	private List<SecurityContext> securityContext() {

		return Lists.newArrayList(
				SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex(PRODUCT_ID))
						.build(),
				SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex(CUSTOMER))
						.build(),
				SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex(ADDRESS))
						.build(),
				SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex(CREDIT))
						.build(),
				SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex(ORDERS))
						.build(),
				SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex(ORDERS_ID))
						.build(),
				SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex(INCUSTOMER))
						.build(),
				SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex(SHORTDETAIL))
						.build());

	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Lists.newArrayList(new SecurityReference("UserSecurity", authorizationScopes));
	}
}
