/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.pollsTest.services;

import com.rest.pollsTest.models.User;
import java.util.HashMap;
import java.util.Map;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Julian Olarte Torres
 */
public class AuthService {
    private final JDBCService jdbcService;
    
    public AuthService() {
        this.jdbcService = new JDBCService();
    }
    
    public Map<String, Object> login(String username, String password) {
        String query = String.format("SELECT * FROM users WHERE username = '%s' AND password = '%s'", username, password);
        HashMap<String, Object> response = new HashMap<>();
        try {
            Map<String, Object> responseUser = this.jdbcService.getConnection().queryForMap(query);
            User user = new User(
                (Integer)(responseUser.get("id")),
                (String)(responseUser.get("username")),
                (String)(responseUser.get("password")),
                (String)(responseUser.get("role")),
                (String)(responseUser.get("createdAt").toString()),
                (String)(responseUser.get("updatedAt").toString())
            );
            response.put("status", true);
            response.put("user", user);
        }
        catch(DataAccessException ex) {
            response.put("status", false);
            response.put("message", "Unauthenticated");
        }
        return response;
    }
}
