package com.turing.ecommerce.controllers;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filter to log request and response to the application
 * 
 * @author ThankGod Ukachukwu
 */



@Component
public class TuringEcommerceFilter implements Filter {
	
	private final static Logger LOG = LoggerFactory.getLogger(TuringEcommerceFilter.class);

	@Override
	public void init(FilterConfig filterConfig) {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		
		
		
		HttpServletRequest req = (HttpServletRequest) servletRequest;
		HttpServletResponse res = (HttpServletResponse) servletResponse;
		LOG.info("Logging Request  {} : {}", req.getMethod(), req.getRequestURI());
		filterChain.doFilter(servletRequest, servletResponse);
		LOG.info("Logging Response :{}", res.getContentType());
	}

	@Override
	public void destroy() {
	}

}
