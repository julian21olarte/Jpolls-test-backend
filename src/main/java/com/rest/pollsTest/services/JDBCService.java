/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.pollsTest.services;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author Julian Olarte Torres
 */
public class JDBCService {
    
    private final JdbcTemplate jdbcTemplate;
    
    public JDBCService() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/polls_test");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public JdbcTemplate getConnection() {
        return this.jdbcTemplate;
    }
}
