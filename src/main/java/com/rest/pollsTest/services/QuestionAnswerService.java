/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.pollsTest.services;

import java.util.List;
import java.util.Map;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Julian Olarte Torres
 */
public class QuestionAnswerService {
    
    private final JDBCService JdbService;
    private final RandomStringGenerator randomUuid;

    public QuestionAnswerService() {
        this.JdbService = new JDBCService();
        this.randomUuid = new RandomStringGenerator.Builder()
            .withinRange('0', 'z')
            .filteredBy(t -> t >= '0' && t <= '9', t -> t >= 'A' && t <= 'Z', t -> t >= 'a' && t <= 'z')
            .build();
    }
    
    public List getResponsesByPollId(Integer pollId) {
        String query = String.format("SELECT * FROM questionanswers WHERE pollId = %s", pollId);
        return this.JdbService.getConnection().queryForList(query);
    }
    
    public List<Map<String, Object>> saveMany(List<Map<String, Object>> questions) throws Exception {
        String uuid = this.randomUuid.generate(10);
        String query;
        JdbcTemplate connection = this.JdbService.getConnection();
        for(Map<String, Object> question: questions) {
            query = String.format("INSERT INTO questionanswers(pollId, questionId, answerId, sessionId, createdAt, updatedAt) "
                + "VALUES(%s, %s, %s, '%s', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)",
                (Integer)question.get("pollId"),
                (Integer)question.get("id"),
                (Integer)question.get("answer"),
                uuid);
            connection.update(query);
        }
        query = String.format("SELECT q.description AS question, a.description AS answer "
            + "FROM questionanswers qs INNER JOIN questions q ON q.id = qa.questionId "
            + "INNER JOIN answers a ON a.id = qa.answerId WHERE pollId = %s", 
            (Integer)(questions.get(0).get("pollId")));
        
        List<Map<String, Object>> responses = connection.queryForList(query);
        return responses;
    }
}
