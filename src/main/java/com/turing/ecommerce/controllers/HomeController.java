/**
 * 
 */
package com.turing.ecommerce.controllers;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author frankukachukwu
 *
 */



@RestController
public class HomeController {
     
    @GetMapping("/")
    public ResponseEntity<?> homepage() throws URISyntaxException{
   
        return ResponseEntity.created(new URI("/api")).build();
    }
}


