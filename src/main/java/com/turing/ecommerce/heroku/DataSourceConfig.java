/**
 * 
 */
package com.turing.ecommerce.heroku;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author frankukachukwu
 *
 */
//@Configuration
public class DataSourceConfig {
     
    //@Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://ec2-107-22-211-248.compute-1.amazonaws.com:5432/d9q0v8jjrtjjjv?user="
        		+ "ilfwkeybglsxxy&password=afa4e1cf40e23b8c3f7bfbe5f37f607ef3933140dd6ed7243220a587d894d32d&sslmode=require");
        dataSourceBuilder.username("ilfwkeybglsxxy");
        dataSourceBuilder.password("afa4e1cf40e23b8c3f7bfbe5f37f607ef3933140dd6ed7243220a587d894d32d");
        return dataSourceBuilder.build();
    }
}