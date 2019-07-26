/**
 * 
 */
package com.turing.ecommerce.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;

/**
 * @author thankgodukachukwu
 *
 */
@Data
public class ChargeRequest {

    public enum Currency {
        EUR, USD;
    }
    
    @NotEmpty(message = "Review cannot be emply")
	@NotBlank(message = "Review cannot be emply")
    private String description;
    
    @Positive
    @NotNull
    private int amount; 
    // cents
    private Currency currency;
    @NotEmpty(message = "Review cannot be emply")
   	@NotBlank(message = "Review cannot be emply")
    private String stripeEmail;
    
    @NotEmpty(message = "Review cannot be emply")
   	@NotBlank(message = "Review cannot be emply")
    private String stripeToken;
    
    @Positive
    @NotNull
    private Integer orderId;
}