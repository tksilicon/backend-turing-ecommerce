package com.turing.ecommerce.security.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turing.ecommerce.exceptions.error;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		log.debug("Jwt authentication failed:" + authException);

		
		
		
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
    	response.setContentType("application/json");
    	response.setCharacterEncoding("UTF-8");
    
    
    		error exceptionResponse = new error(String.valueOf(HttpStatus.UNAUTHORIZED.value()), "AUT_02", 
    				"Jwt authentication failed", "NoAuth");

        response.getOutputStream()
          .println(objectMapper.writeValueAsString(exceptionResponse));

	}

}
