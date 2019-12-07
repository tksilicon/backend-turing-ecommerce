/**
 * 
 */
package com.turing.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.turing.ecommerce.security.jwt.JwtConfigurer;
import com.turing.ecommerce.security.jwt.JwtTokenProvider;
import com.turing.ecommerce.service.CustomCustomerDetailsService;

/**
 * @author thankgodukachukwu
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	CustomCustomerDetailsService customCustomerDetailsService;
	
	@Override
    public void configure(WebSecurity web) throws Exception {
		  // ignore swagger 
		web.ignoring().antMatchers("/v2/api-docs", "/configuration/**", 
        		"/swagger-resources/**",  "/swagger-ui.html", "/webjars/**", "/api-docs/**","/", 
        		"/webjars/springfox-swagger-ui/**");
        		
        		
 
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();

	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(encoder());

		return authProvider;

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authenticationProvider());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.httpBasic().disable().csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				
				
				.antMatchers("/api/customer").authenticated()
				.antMatchers("/api/products/{product_id}/reviews").authenticated()
				.antMatchers("/api/customers/address").authenticated()
				.antMatchers("/api/customers/creditCard").authenticated()
				.antMatchers("/api/orders").authenticated()
				.antMatchers("/api/orders/{order_id}").authenticated()
				.antMatchers("/api/orders/inCustomer").authenticated().
				antMatchers("/api/orders/shortDetail/{order_id}").authenticated()
			    
				.and()
				

				.apply(new JwtConfigurer(jwtTokenProvider));

	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {

		return new CustomCustomerDetailsService();

	}

}
