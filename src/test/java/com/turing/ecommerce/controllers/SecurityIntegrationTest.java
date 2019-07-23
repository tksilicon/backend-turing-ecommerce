/**
 * 
 */
package com.turing.ecommerce.controllers;

import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turing.ecommerce.DTO.AuthenticationRequest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author thankgodukachukwu
 *
 */

@ContextConfiguration
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class SecurityIntegrationTest {
	
	
	
	@LocalServerPort
    int port;
	

    @Autowired
    ObjectMapper objectMapper;

    private String token;
	
	@Before
	public void setup() {
		

		RestAssured.port = 8080;
		token = given().contentType(ContentType.JSON)
				.body(AuthenticationRequest.builder().username("frankgod02@gmail.com").password("123456").build())
				.when().post("/api/customers/login").andReturn().jsonPath().getString("token");
		log.debug("Got token:" + token);

	}
	
	
	 @Test
	    public void getCustomer() throws Exception {
	        //@formatter:off
	         given()

	            .accept(ContentType.JSON)

	        .when()
	            .get("http://localhost:" + port +"/api/customer")

	        .then()
	            .assertThat()
	            .statusCode(HttpStatus.SC_OK);
	         //@formatter:on
	    }

}
