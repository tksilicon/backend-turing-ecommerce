/**
 * 
 */
package com.turing.ecommerce.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author frankukachukwu
 *
 */
@Component
public class FacebookService {

    @Value("${spring.social.facebook.appId}")
    String facebookAppId;
    @Value("${spring.social.facebook.appSecret}")
    String facebookSecret;
    
    String accessToken;
    
 
    
    public String createFacebookAccessToken(String code) {
        FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(facebookAppId, facebookSecret);
        AccessGrant accessGrant = connectionFactory.getOAuthOperations().exchangeForAccess(code, "https://backend-turing-ecommerce.herokuapp.com/api/customers/facebook/", null);
        accessToken = accessGrant.getAccessToken();
    
        
        return  accessToken;
    }
    
    public String getName() {
        Facebook facebook = new FacebookTemplate(accessToken);
        String[] fields = {"id", "name"};
        return facebook.fetchObject("me", String.class, fields).toString();
    }

}