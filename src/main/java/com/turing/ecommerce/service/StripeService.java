/**
 * 
 */
package com.turing.ecommerce.service;

import com.stripe.exception.ApiConnectionException;
import com.stripe.exception.ApiException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.turing.ecommerce.DTO.ChargeRequest;

/**
 * @author thankgodukachukwu
 *
 */
public interface StripeService {
	
	public Charge charge(ChargeRequest chargeRequest) 
			throws AuthenticationException, InvalidRequestException, ApiConnectionException, CardException, ApiException, StripeException;

}
