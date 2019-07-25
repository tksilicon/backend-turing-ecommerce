/**
 * 
 */
package com.turing.ecommerce.security;

/**
 * @author thankgodukachukwu
 *
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.turing.ecommerce.security.jwt.JwtAuthenticationEntryPoint;
import com.turing.ecommerce.security.jwt.JwtConfigurer;
import com.turing.ecommerce.security.jwt.JwtTokenProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MySavedRequestAwareAuthenticationSuccessHandler mySavedRequestAwareAuthenticationSuccessHandler;

	private SimpleUrlAuthenticationFailureHandler myFailureHandler = new SimpleUrlAuthenticationFailureHandler();
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
		
		
	}

	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http //.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
		//.and()
		     .httpBasic().disable()
		     .csrf().disable()
		     .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				    .authorizeRequests()
				    .antMatchers("/api/products/{product_id}/reviews").authenticated()
				    .antMatchers("/api/customer").authenticated()
				    .antMatchers("/api/customers/address").authenticated()
				    .antMatchers("/api/customers/creditCard").authenticated()
				    .antMatchers("/api//orders").authenticated()
				    .antMatchers("/api/orders/{order_id}").authenticated()
				    .antMatchers("/api/orders/inCustomer").authenticated()
				    .antMatchers("/api/orders/shortDetail/{order_id}").authenticated()
				    .antMatchers("/api/stripe/charge").authenticated()
				    
				
				.and()

				.formLogin().successHandler(mySavedRequestAwareAuthenticationSuccessHandler)
				.failureHandler(myFailureHandler)
				.and().logout()
				.and()
				.apply(new JwtConfigurer(jwtTokenProvider));;
		       

	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}


