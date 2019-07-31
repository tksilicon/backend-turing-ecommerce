/**
 * 
 */
package com.turing.ecommerce.controllers;

import com.stripe.Stripe;
/**
 * @author frankukachukwu
 *
 */
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Event;
import com.stripe.model.WebhookEndpoint;
import com.turing.ecommerce.DTO.ChargeRequest;
import com.turing.ecommerce.DTO.ProductGetAllDTO;
import com.turing.ecommerce.DTO.Unauthorized;
import com.turing.ecommerce.DTO.ChargeRequest.Currency;
import com.turing.ecommerce.exceptions.error;
import com.turing.ecommerce.service.StripeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Everything about Stripe Ingregation and Webhooks")
@RestController
public class ChargeController {

	@Resource(name = "stripeServiceImpl")
    StripeService paymentsService;
	/*
	 * API to search all products
	 */
	@ApiOperation(value = "This method recieves a frond-end payment and creates a charge", response = Map.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Object from Stripe", response = Charge.class ),
            @ApiResponse(code = 400, message = "Return a error object", response = error.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Unauthorized.class)})
	@CrossOrigin
	@PostMapping("/api/stripe/charge")
    public ResponseEntity<Charge> charge(
    		
    		
    		@RequestParam(name = "stripeToken", required = true) String stripeToken,
			@RequestParam(name = "order_id", required = true ) Integer order_id,
			@RequestParam(name = "description", required = true ) String description,
			@RequestParam(name = "amount", required = true ) Integer amount,
			@RequestParam(name = "currency", required = true, defaultValue="USD") String currency, Model model) throws StripeException {
        
		
		ChargeRequest chargeRequest = new ChargeRequest();
		chargeRequest.setDescription(description);
		
		if(currency.toLowerCase().trim().equals("eur")) {
			chargeRequest.setCurrency("EUR");
		}else {
			chargeRequest.setCurrency("USD");
		}
		chargeRequest.setStripeToken(stripeToken);
		chargeRequest.setOrderId(order_id);
		chargeRequest.setAmount(amount);
		
        
        Charge charge = paymentsService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
      
        
        return ResponseEntity.ok(charge);
    }
	@CrossOrigin
    @PostMapping("/api/stripe/webhooks")
    public ResponseEntity webhooks(Model model) throws StripeException {
    	Stripe.apiKey = "sk_test_lomdOfxbm7QDgZWvR82UhV6D";

    	Map<String, Object> webhookendpointParams = new HashMap<String, Object>();
    	webhookendpointParams.put("url", "https://backend-turing-ecommerce.herokuapp.com/api/stripe/webhooks");
    	webhookendpointParams.put("enabled_events", Arrays.asList("charge.failed", "charge.succeeded"));

    	WebhookEndpoint endpoint = WebhookEndpoint.create(webhookendpointParams);
        return ResponseEntity.ok(endpoint);
    }
    
    
   
    

    
}
