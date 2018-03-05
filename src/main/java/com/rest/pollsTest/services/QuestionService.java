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
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Julian Olarte Torres
 */
public class QuestionService {
    private final JDBCService JdbService;
    private final AnswerService answerService;

    public QuestionService() {
        this.JdbService = new JDBCService();
        this.answerService = new AnswerService();
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
    
    
    public void saveMany(List<Map<String, Object>> questions) throws Exception{
        JdbcTemplate connection = this.JdbService.getConnection();
        for(Map<String, Object> question: questions) {
            String query = String.format("INSERT INTO questions(description, pollId, createdAt, updatedAt) "
                + "Values('%s', %s, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)",
                (String)(question.get("description")),
                (Integer)(question.get("pollId")));
            connection.update(query);
            
            query = "SELECT id FROM questions ORDER BY id DESC LIMIT 1";
            Map<String, Object> questionsInserted = connection.queryForMap(query);
            Integer questionId = (Integer)(questionsInserted.get("id"));
            List<Map<String, Object>> answers = (List<Map<String, Object>>)(question.get("answers"));
            answers.forEach((answer) -> {
                answer.put("questionId", questionId);
            });
            this.answerService.saveMany(answers);
        }
    }
    
}
