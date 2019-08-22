package com.turing.ecommerce.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FilterConfig {

    // uncomment this and comment the @Component in the filter class definition to register only for a url pattern
    // @Bean
    public FilterRegistrationBean<TuringEcommerceFilter> loggingFilter() {
        FilterRegistrationBean<TuringEcommerceFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new TuringEcommerceFilter());

        //registrationBean.addUrlPatterns("/users/*");

        return registrationBean;

    }

}
