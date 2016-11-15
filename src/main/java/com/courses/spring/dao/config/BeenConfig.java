package com.courses.spring.dao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@Configuration
public class BeenConfig {

    @Bean(name="dataSource")
    public DataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/STUDENT_DB");
        driverManagerDataSource.setUsername("sergei");
        driverManagerDataSource.setPassword("sergei");
        return  driverManagerDataSource;
    }

    @Bean(name="namedParameterJdbcTemplate")
    public NamedParameterJdbcOperations namedParameterJdbcTemplate(){
        return new NamedParameterJdbcTemplate(dataSource());
    }

}
