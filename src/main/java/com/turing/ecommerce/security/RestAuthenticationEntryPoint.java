/**
 * 
 */
package com.turing.ecommerce.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.turing.ecommerce.exceptions.error;

/**
 * @author thankgodukachukwu
 *
 */
@Component
public final class RestAuthenticationEntryPoint 
  implements AuthenticationEntryPoint {
 
	private ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
        throws IOException, ServletException {
        //401
    	
    	response.setStatus(HttpStatus.UNAUTHORIZED.value());
    	response.setContentType("application/json");
    	response.setCharacterEncoding("UTF-8");
    
    
    		error exceptionResponse = new error(String.valueOf(HttpStatus.UNAUTHORIZED.value()), "AUT_01", 
    				"There is no authentication", "NoAuth");

        response.getOutputStream()
          .println(objectMapper.writeValueAsString(exceptionResponse));
    	    	

      
    }
    
    
    @ExceptionHandler (value = {AccessDeniedException.class})
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AccessDeniedException accessDeniedException) throws IOException {
      // 403
      response.sendError(HttpServletResponse.SC_FORBIDDEN, "Authorization Failed : " + accessDeniedException.getMessage());
    }

    @ExceptionHandler (value = {Exception.class})
    public void commence(HttpServletRequest request, HttpServletResponse response,
        Exception exception) throws IOException {
       // 500
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error : " + exception.getMessage());
    }
}
