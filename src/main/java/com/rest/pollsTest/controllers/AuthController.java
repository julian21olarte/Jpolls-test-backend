/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.pollsTest.controllers;

import com.google.gson.Gson;
import com.rest.pollsTest.services.AuthService;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Julian Olarte Torres
 */
@RestController
public class AuthController {
    private final AuthService authService;
    
    public AuthController() {
        this.authService = new AuthService();
    }
    
    @RequestMapping(value = "/auth/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String login(@RequestBody Map<String, Object> request) {
        String username = (String) request.get("username");
        String password = (String) request.get("password");
        Map<String, Object> responseUser = this.authService.login(username, password);
        System.out.println(responseUser);
        Gson gsonResponse = new Gson();
        if(((Boolean)responseUser.get("status"))) {
            return gsonResponse.toJson(responseUser.get("user"));
        }
        responseUser.put("status", 401);
        return gsonResponse.toJson(responseUser);
    }
    
    
    @RequestMapping(value = "/auth/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String logout() {
        return "";
    }
}
