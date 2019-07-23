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
import com.turing.ecommerce.DTO.ChargeRequest.Currency;
import com.turing.ecommerce.service.StripeService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ChargeController {

	@Resource(name = "stripeServiceImpl")
    StripeService paymentsService;

    @PostMapping("/api/stripe/charge")
    public String charge(ChargeRequest chargeRequest, Model model) throws StripeException {
        chargeRequest.setDescription("Customer charge");
        chargeRequest.setCurrency(Currency.USD);
        Charge charge = paymentsService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        return "result";
    }
    
    @PostMapping("/api/stripe/webhooks")
    public ResponseEntity webhooks(Model model) throws StripeException {
    	Stripe.apiKey = "sk_test_lomdOfxbm7QDgZWvR82UhV6D";

    	Map<String, Object> webhookendpointParams = new HashMap<String, Object>();
    	webhookendpointParams.put("url", "https://example.com/my/webhook/endpoint");
    	webhookendpointParams.put("enabled_events", Arrays.asList("charge.failed", "charge.succeeded"));

    	WebhookEndpoint endpoint = WebhookEndpoint.create(webhookendpointParams);
        return ResponseEntity.ok(endpoint);
    }
    
    
   
    

    
}
