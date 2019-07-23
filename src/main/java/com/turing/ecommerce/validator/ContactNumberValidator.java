/**
 * 
 */
package com.turing.ecommerce.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author thankgodukachukwu
 *
 */
public class ContactNumberValidator implements ConstraintValidator< PhoneNumberCheck, String> {

  @Override
  public void initialize(PhoneNumberCheck contactNumber) {
  }
  
  

  @Override
  public boolean isValid(String contactField, ConstraintValidatorContext cxt) {
      return contactField != null && contactField.matches("^[+]?\\d+$")
        && (contactField.length() > 8) && (contactField.length() <= 16);
  }

}