/**
 * 
 */
package com.turing.ecommerce.DTO;

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
    private String description;
    private int amount; 
    // cents
    private Currency currency;
    private String stripeEmail;
    private String stripeToken;
    @Positive
    @NotNull
    private Integer orderId;
}