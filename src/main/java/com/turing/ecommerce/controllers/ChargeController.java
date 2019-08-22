/**
 * 
 */
package com.turing.ecommerce.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.Stripe;
import com.stripe.exception.ApiConnectionException;
import com.stripe.exception.ApiException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.WebhookEndpoint;
import com.turing.ecommerce.DTO.ChargeRequest;
import com.turing.ecommerce.DTO.StripePayObject;
import com.turing.ecommerce.DTO.Unauthorized;
import com.turing.ecommerce.exceptions.ChargeException;
import com.turing.ecommerce.exceptions.error;
import com.turing.ecommerce.service.StripeServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
/**
 * @author thankgodukachukwu
 *
 */

@Api(value = "Everything about Stripe Ingregation and Webhooks, "
		+ " Generate token for care from - https://codepen.io/fmartingr/pen/pGfhy"
		+ "Card Num:4242424242424242, Exp Month/ Year: 8/2020, CVC 314, public key: pk_test_fcL8nZaQrozBEVAbnAD6mG2M00AxMWLiia")
@RestController
public class ChargeController {

	private StripeServiceImpl stripeClient;

	@Autowired
	ChargeController(StripeServiceImpl stripeClient) {
		this.stripeClient = stripeClient;
	}

	/*
	 * 
	 * 
	 * Generate token for care from - https://codepen.io/fmartingr/pen/pGfhy Using
	 * sample card:
	 * 
	 * Card Num:4242424242424242 Exp Month/ Year: 8/2020 CVC 314
	 * 
	 * 4000056655665556
	 * 
	 * public key: pk_test_fcL8nZaQrozBEVAbnAD6mG2M00AxMWLiia
	 * 
	 * Card token: tok_1F2TBmL2eGgSu8AhlWtSB7BB
	 * 
	 */

	@CrossOrigin
	@ApiOperation(value = "This method recieves a frond-end payment and creates a charge")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Object from Stripe", response=StripePayObject.class),
			@ApiResponse(code = 400, message = "Return a error object", response = error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Unauthorized.class) })

	@PostMapping("/api/stripe/charge")
	public ResponseEntity<StripePayObject> charge(

			@RequestParam(name = "aStripeToken", required = true) String aStripeToken,
			@RequestParam(name = "order_id", required = true) Integer order_id,
			@RequestParam(name = "description", required = true) String description,
			@RequestParam(name = "amount", required = true) Integer amount,
			@RequestParam(name = "currency", required = false, defaultValue = "USD") String currency) throws AuthenticationException, InvalidRequestException, ApiConnectionException, CardException, ApiException, StripeException {

		ChargeRequest chargeRequest = new ChargeRequest();
		chargeRequest.setDescription(description);

		chargeRequest.setCurrency(currency);

		chargeRequest.setStripeToken(aStripeToken);
		chargeRequest.setOrderId(order_id);
		chargeRequest.setAmount(amount);

		Charge charge = stripeClient.charge(chargeRequest);
		
		
		StripePayObject striper = new StripePayObject();
		striper.setAmount(charge.getAmount());
		striper.setDescription(description);
		striper.setOrderId(order_id);
		
		
		striper.setStatus((charge.getStatus()));
		
		return ResponseEntity.ok(striper);

		

	}

	@CrossOrigin
	@PostMapping("/api/stripe/webhooks")
	public ResponseEntity webhooks(Model model) throws StripeException {
		Stripe.apiKey = "sk_test_lomdOfxbm7QDgZWvR82UhV6D";

		Map<String, Object> webhookendpointParams = new HashMap<>();
		webhookendpointParams.put("url", "https://backend-turing-ecommerce.herokuapp.com/api/stripe/webhooks");
		webhookendpointParams.put("enabled_events", Arrays.asList("charge.failed", "charge.succeeded"));

		WebhookEndpoint endpoint = WebhookEndpoint.create(webhookendpointParams);
		return ResponseEntity.ok(endpoint);
	}

}
