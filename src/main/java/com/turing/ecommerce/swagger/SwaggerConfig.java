/**
 * 
 */
package com.turing.ecommerce.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import static springfox.documentation.builders.PathSelectors.regex;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;

import java.util.ArrayList;
import java.util.List;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;

/**
 * @author thankgodukachukwu
 *
 */


@Configuration
@EnableSwagger2

public class SwaggerConfig {

        
    @Bean
    public Docket apiDocket() {
        
        Contact contact = new Contact(
                "ThankGod Ukachukwu",
                "https://github.com/tksilicon/backend-turing-ecommerce", 
                "frankgod02@hotmail.com"
        );
        
        List<VendorExtension> vendorExtensions = new ArrayList<>();
        
        ApiInfo apiInfo = new ApiInfo(
       "Turing Ecommerce RESTful API documentation", 
       "This pages documents Turing Ecommerce RESTful API endpoints", "1.0",
       "https://github.com/tksilicon/backend-turing-ecommerce", contact, 
       "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",vendorExtensions);
        
        Docket docket =  new Docket(DocumentationType.SWAGGER_2)
                 .apiInfo(apiInfo)
                 .select()
                 .apis(RequestHandlerSelectors.basePackage("com.turing.ecommerce.controllers"))
                 .paths(PathSelectors.any())
                 .build();
        
        return docket;
        
     } 
}
