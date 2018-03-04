/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.pollsTest.services;

import com.rest.pollsTest.models.Question;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Julian Olarte Torres
 */
public class QuestionService {
    private final JDBCService JdbService;

    public QuestionService() {
        this.JdbService = new JDBCService();
    }
    
    public List getQuestions() {
        String query = "SELECT * FROM questions";
        return this.JdbService.getConnection().queryForList(query);
    }
    
    public List getQuestionsByPollId(Integer pollId) {
        String query = String.format("SELECT * FROM questions WHERE pollId = %s", pollId);
        List<Question> questions = new ArrayList();
        
        List<Map<String, Object>> pollsResult = this.JdbService.getConnection().queryForList(query);
        pollsResult.forEach((question) -> {
            questions.add( new Question(
                    (Integer)(question.get("id")),
                    (String)(question.get("description").toString()), 
                    pollId,
                    (String)(question.get("createdAt").toString()),
                    (String)(question.get("updatedAt").toString())    
            ));
        });
        return questions;
    }
    
    public Question getQuestionById(Integer questionId) {
        String query = String.format("SELECT * FROM questions WHERE id = %s", questionId);
        Map<String, Object> question = this.JdbService.getConnection().queryForMap(query);
        return new Question(
            (Integer)(question.get("id")),
            (String)(question.get("description").toString()), 
            (Integer)(question.get("pollId")),
            (String)(question.get("createdAt").toString()),
            (String)(question.get("updatedAt").toString())    
        );
    }
    
}
