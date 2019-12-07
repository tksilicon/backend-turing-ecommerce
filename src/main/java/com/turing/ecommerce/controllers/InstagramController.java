/**
 * 
 */
package com.turing.ecommerce.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author thankgodukachukwu
 *
 */
@RestController
public class InstagramController {
	
	
	@GetMapping("/api/auth/instagram/callback")
	public ResponseEntity home() {
		
		//access_token=1320542743.5bdfaf5.caeef230322c4a79a70c78dbf4288d06

		
			return ResponseEntity.noContent().build();

		
	}

}
