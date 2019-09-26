/**
 * 
 */
package com.turing.ecommerce.service;

/**
 * @author thankgodukachukwu
 *
 */

import com.stripe.Stripe;
import com.stripe.exception.ApiConnectionException;
import com.stripe.exception.ApiException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import com.turing.ecommerce.DTO.ChargeRequest;
import com.turing.ecommerce.config.TuringEcommerceFilter;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class StripeServiceImpl{

	private final static Logger LOG = LoggerFactory.getLogger(StripeServiceImpl.class);
	
	
	 @Autowired
	 StripeServiceImpl() {
		  Stripe.apiKey = "sk_test_vvpKl3yIpBVeoRJ3qxS6mjIF00jfi6Bi6j";
	    }

  
	public Charge charge(ChargeRequest chargeRequest) throws StripeException {
      
		Map<String, Object> chargeParams = new HashMap<>();
		chargeParams.put("amount", chargeRequest.getAmount());
		chargeParams.put("currency", chargeRequest.getCurrency());
		chargeParams.put("description", chargeRequest.getDescription());
		chargeParams.put("source", chargeRequest.getStripeToken());
		 LOG.error(chargeRequest.getStripeToken());
		 LOG.info(chargeRequest.getStripeToken());
		return Charge.create(chargeParams);

	}

}
