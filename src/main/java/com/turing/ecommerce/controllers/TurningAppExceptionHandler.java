package com.turing.ecommerce.controllers;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.stripe.exception.StripeException;
import com.turing.ecommerce.exceptions.ApiErrorResponse;
import com.turing.ecommerce.exceptions.CustomerExistException;
import com.turing.ecommerce.exceptions.CustomerNotFoundException;
import com.turing.ecommerce.exceptions.OrderDetailNotFoundException;
import com.turing.ecommerce.exceptions.ProductNotFoundException;
import com.turing.ecommerce.exceptions.ProductsGetProductsException;

import lombok.extern.slf4j.Slf4j;

/**
 * Exception handler for all exceptions.
 */
@Slf4j
@RestControllerAdvice
public class TurningAppExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ProductsGetProductsException.class)
	public final ResponseEntity<ApiErrorResponse> handleGetProductsException(ProductsGetProductsException ex,
			WebRequest request) {

		List<Object> params = ex.getParams();

		String output = "";
		for (int i = 0; i < params.size(); i++)
			output += params.get(i) + ", ";

		ApiErrorResponse exceptionResponse = new ApiErrorResponse("USR_11", ex.getMessage(), output,
				String.valueOf(HttpStatus.BAD_REQUEST.value()));

		return new ResponseEntity<ApiErrorResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);

	}

	/**
	 * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid
	 * validation.
	 *
	 * @param ex      the MethodArgumentNotValidException that is thrown when @Valid
	 *                validation fails
	 * @param headers HttpHeaders
	 * @param status  HttpStatus
	 * @param request WebRequest
	 * @return the ApiError object
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ApiErrorResponse errorResponse = new ApiErrorResponse("USR_02", String.valueOf(HttpStatus.BAD_REQUEST));

		errorResponse.addValidationErrors(ex.getBindingResult().getFieldErrors());

		return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles javax.validation.ConstraintViolationException. Thrown when @Validated
	 * fails.
	 *
	 * @param ex the ConstraintViolationException
	 * @return the ApiError object
	 */
	@ExceptionHandler( CustomerNotFoundException.class)
	public final ResponseEntity<ApiErrorResponse> handleConstraintViolationPostCustomer(CustomerNotFoundException ex) {

		ApiErrorResponse errorResponse = new ApiErrorResponse("USR_21", ex.getMessage(),
				"JSon String", String.valueOf(HttpStatus.BAD_REQUEST));

		return new ResponseEntity<ApiErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles javax.validation.ConstraintViolationException. Thrown when @Validated
	 * fails.
	 *
	 * @param ex the ConstraintViolationException
	 * @return the ApiError object
	 */
	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
	public final ResponseEntity<ApiErrorResponse> handleConstraintViolation(
			javax.validation.ConstraintViolationException ex) {

		ApiErrorResponse errorResponse = new ApiErrorResponse("USR_19", String.valueOf(HttpStatus.BAD_REQUEST));
		errorResponse.addValidationErrors(ex.getConstraintViolations());

		return new ResponseEntity<ApiErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle HttpMessageNotReadableException. Happens when request JSON is
	 * malformed.
	 *
	 * @param ex      HttpMessageNotReadableException
	 * @param headers HttpHeaders
	 * @param status  HttpStatus
	 * @param request WebRequest
	 * @return the ApiError object
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ServletWebRequest servletWebRequest = (ServletWebRequest) request;
		log.info("{} to {}", servletWebRequest.getHttpMethod(), servletWebRequest.getRequest().getServletPath());

		ApiErrorResponse errorResponse = new ApiErrorResponse("USR_20", "Malformed JSON request", "JSon String",
				String.valueOf(HttpStatus.BAD_REQUEST));
		return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle DataIntegrityViolationException, inspects the cause for different DB
	 * causes.
	 *
	 * @param ex the DataIntegrityViolationException
	 * @return  the ApiErrorResponse object
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	public final ResponseEntity<ApiErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex,
			WebRequest request) {

		if (ex.getCause() instanceof ConstraintViolationException) {
			javax.validation.ConstraintViolationException exx = (javax.validation.ConstraintViolationException) ex
					.getCause();

			ApiErrorResponse errorResponse = new ApiErrorResponse("USR_19", String.valueOf(HttpStatus.BAD_REQUEST));
			errorResponse.addValidationErrors(exx.getConstraintViolations());
		}
		ApiErrorResponse errorResponse = new ApiErrorResponse("USR_DATA", ex.getMessage(), ex.getCause().getMessage(),
				String.valueOf(HttpStatus.BAD_REQUEST));

		return new ResponseEntity<ApiErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Handles org.springframework.security.core.userdetails.UsernameNotFoundException. 
	 * fails.
	 *
	 * @param ex the UsernameNotFoundException
	 * @return the ApiErrorResponse object
	 */
	@ExceptionHandler(UsernameNotFoundException.class)
	public final ResponseEntity<ApiErrorResponse> handleUsernameNotFoundException(
			UsernameNotFoundException ex) {
		
		
		ApiErrorResponse errorResponse = new ApiErrorResponse("USR_05", "The email doesn't exist.",
				"Email", String.valueOf(HttpStatus.BAD_REQUEST));

		return new ResponseEntity<ApiErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	

	/**
	 * Handles OrderDetailNotFoundException
	 * 
	 *
	 * @param ex the OrderDetailNotFoundException
	 * @return the ApiErrorResponse object
	 */
	@ExceptionHandler(OrderDetailNotFoundException.class)
	public final ResponseEntity<ApiErrorResponse> handleOrderDetailNotFoundException(
			OrderDetailNotFoundException ex) {
		
		
		ApiErrorResponse errorResponse = new ApiErrorResponse("Endpoint not found.");

		return new ResponseEntity<ApiErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Handles StripeException.
	 * 
	 *
	 * @param ex the StripeException.
	 * @return the ApiErrorResponse object
	 */
	@ExceptionHandler(StripeException.class)
	public final ResponseEntity<ApiErrorResponse> handleStripeException(
			StripeException ex) {
		
		
		ApiErrorResponse errorResponse = new ApiErrorResponse("USR_10", ex.getCause().getMessage(),
				"Stripe", String.valueOf(HttpStatus.BAD_REQUEST));


		return new ResponseEntity<ApiErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Handles CustomerExistException 
	 * fails.
	 *
	 * @param ex the CustomerExistException
	 * @return the ApiErrorResponse object
	 */
	@ExceptionHandler(CustomerExistException.class)
	public final ResponseEntity<ApiErrorResponse> handleCustomerExistException(
			CustomerExistException ex) {
		
		
		ApiErrorResponse errorResponse = new ApiErrorResponse("USR_04", "The email already exists."
				+ "Email", String.valueOf(HttpStatus.BAD_REQUEST));

		return new ResponseEntity<ApiErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Handles BadCredentialsException 
	 * fails.
	 *
	 * @param ex the BadCredentialsException
	 * @return the ApiErrorResponse object
	 */
	@ExceptionHandler(BadCredentialsException.class)
	public final ResponseEntity<ApiErrorResponse> handleBadCredentialsException(
			BadCredentialsException ex) {
		
		
		ApiErrorResponse errorResponse = new ApiErrorResponse("USR_01", "Email or Password is invalid",
				"Email/Password", String.valueOf(HttpStatus.BAD_REQUEST));

		return new ResponseEntity<ApiErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	
	/**
	 * Handles ProductNotFoundException
	 * fails.
	 *
	 * @param ex the ProductNotFoundException
	 * @return the ApiErrorResponse object
	 */
	@ExceptionHandler(ProductNotFoundException.class)
	public final ResponseEntity<ApiErrorResponse> handleProductNotFoundException(
			ProductNotFoundException ex) {
		
		
		ApiErrorResponse errorResponse = new ApiErrorResponse("PRO_01", "Product Not found"
				+ "Product", String.valueOf(HttpStatus.NOT_FOUND));

		return new ResponseEntity<ApiErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	/**
	 * Handles javax.persistence.RollbackException
	 * fails.
	 *
	 * @param ex the javax.persistence.RollbackException
	 * @return the ApiErrorResponse object
	 */
	@ExceptionHandler(javax.persistence.RollbackException.class)
	public final ResponseEntity<ApiErrorResponse> handleBadCredentialsException(
			javax.persistence.RollbackException ex) {
		
		
		ApiErrorResponse errorResponse = new ApiErrorResponse("USR_P1", ex.getMessage(),
				ex.getCause().getMessage(), String.valueOf(HttpStatus.BAD_REQUEST));

		return new ResponseEntity<ApiErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	
	 /**
     * Handle javax.persistence.EntityNotFoundException
     */
    @ExceptionHandler(javax.persistence.EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(javax.persistence.EntityNotFoundException ex) {
    	ApiErrorResponse errorResponse = new ApiErrorResponse("CAT_01", ex.getMessage(),
				ex.getCause().getMessage(), String.valueOf(HttpStatus.BAD_REQUEST));

		return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
	

}
