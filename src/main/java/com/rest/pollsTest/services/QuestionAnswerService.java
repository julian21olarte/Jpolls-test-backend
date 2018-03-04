/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.pollsTest.services;

import java.util.List;

/**
 *
 * @author Julian Olarte Torres
 */
public class QuestionAnswerService {
    
    private final JDBCService JdbService;

    public QuestionAnswerService() {
        this.JdbService = new JDBCService();
    }
    
    public List getResponsesByPollId(Integer pollId) {
        String query = String.format("SELECT * FROM questionanswers WHERE pollId = %s", pollId);
        return this.JdbService.getConnection().queryForList(query);
    }
}
