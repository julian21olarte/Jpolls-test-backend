package com.rest.pollsTest.services;

import com.rest.pollsTest.models.Answer;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Julian Olarte Torres
 */
public class AnswerService {
    private final JDBCService JdbService;

    public AnswerService() {
        this.JdbService = new JDBCService();
    }
    
    public List getQuestions() {
        String query = "SELECT * FROM answers";
        return this.JdbService.getConnection().queryForList(query);
    }
    
    public List getAnswersByQuestionId(Integer questionId) {
        String query = String.format("SELECT * FROM answers WHERE questionId = %s", questionId);
        return this.JdbService.getConnection().queryForList(query);
    }
    
    public Answer getAnswerById(Integer answerId) {
        String query = String.format("SELECT * FROM answers WHERE id = %s", answerId);
        Map<String, Object> answer = this.JdbService.getConnection().queryForMap(query);
        return new Answer(
            (Integer)(answer.get("id")),
            (Integer)(answer.get("questionId")),
            (String)(answer.get("description").toString()), 
            (String)(answer.get("createdAt").toString()),
            (String)(answer.get("updatedAt").toString())    
        );
    }
    
    
    public void saveMany(List<Map<String, Object>> answers) throws Exception{
        JdbcTemplate connection = this.JdbService.getConnection();
        answers.forEach((answer) -> {
            String query = String.format("INSERT INTO answers(description, questionId, createdAt, updatedAt) "
                + "Values('%s', %s, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)",
                (String)(answer.get("description")),
                (Integer)(answer.get("questionId")));
            connection.update(query);
        });  
    }
}
