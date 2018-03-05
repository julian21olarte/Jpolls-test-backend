/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.pollsTest.services;

import com.rest.pollsTest.models.Poll;
import com.rest.pollsTest.models.QuestionAnswer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Julian Olarte Torres
 */
public class PollService {
    private final JDBCService JdbService;
    private final QuestionAnswerService questionAnswerService;
    private final QuestionService questionService;

    public PollService() {
        this.JdbService = new JDBCService();
        this.questionAnswerService = new QuestionAnswerService();
        this.questionService = new QuestionService();
    }
    
    public List getPolls() {
        String query = "SELECT * FROM polls";
        List<Poll> polls = new ArrayList();
        
        List<Map<String, Object>> pollsResult = this.JdbService.getConnection().queryForList(query);
        pollsResult.forEach((poll) -> {
            polls.add( new Poll(
                (Integer)(poll.get("id")),
                (String)(poll.get("title").toString()),
                (String)(poll.get("description").toString()),
                (String)(poll.get("createdAt").toString()),
                (String)(poll.get("updatedAt").toString())    
            ));
        });
        return polls;
    }

    public Poll getPollById(Integer pollId) {
        String query = String.format("SELECT * FROM polls WHERE id = %s", pollId);
        Map<String, Object> pollResult = this.JdbService.getConnection().queryForMap(query);
        return new Poll(
            (Integer)(pollResult.get("id")),
            (String)(pollResult.get("title").toString()),
            (String)(pollResult.get("description").toString()),
            (String)(pollResult.get("createdAt").toString()),
            (String)(pollResult.get("updatedAt").toString())    
        ); 
    }
    
    public Poll getLastPoll() {
        String query = "SELECT * FROM polls ORDER BY createdAt DESC LIMIT 1";
        Map<String, Object> pollResult = this.JdbService.getConnection().queryForMap(query);
        return new Poll(
            (Integer)(pollResult.get("id")),
            (String)(pollResult.get("title").toString()),
            (String)(pollResult.get("description").toString()),
            (String)(pollResult.get("createdAt").toString()),
            (String)(pollResult.get("updatedAt").toString())    
        );
    }
    
    
    public List<QuestionAnswer> getResponsesById(Integer id) {
        return this.questionAnswerService.getResponsesByPollId(id);
    }
    
    
    public Poll savePoll(Map<String, Object> poll) throws Exception {
        JdbcTemplate connection = this.JdbService.getConnection();
        //Get questions from current poll
        List<Map<String, Object>> questions = (List<Map<String, Object>>)(poll.get("questions"));
        
        //Save current poll
        String query = String.format(
            "INSERT INTO polls(title, description, createdAt, updatedAt) "
            + "VALUES('%s', '%s', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)",
            (String)(poll.get("title")),
            (String)(poll.get("description")));
        connection.update(query);
        
        query = "SELECT id FROM polls ORDER BY id DESC LIMIT 1";
        Map<String, Object> pollInsertedId = connection.queryForMap(query);
        Integer pollId = (Integer)(pollInsertedId.get("id"));
        
        //Insert new Poll id in all questions
        questions.forEach((question) -> {
            question.put("pollId", pollId);
        });
        this.questionService.saveMany(questions);
        
        return this.getLastPoll();
    }
    
    
    public Poll updatePoll(Integer id, Map<String, Object> poll) throws Exception {
        this.deletePollById(id);
        JdbcTemplate connection = this.JdbService.getConnection();
        List<Map<String, Object>> questions = (List<Map<String, Object>>)(poll.get("questions"));
        
        //Save current poll
        String query = String.format("INSERT INTO polls(id, title, description, createdAt, updatedAt) "
            + "VALUES(%s, '%s', '%s', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)", id,
            (String)(poll.get("title")),
            (String)(poll.get("description")));
        connection.update(query);        

        questions.forEach((question) -> {
            question.put("pollId", id);
        });
        this.questionService.saveMany(questions);
        
        return this.getLastPoll();
    }

    
    private void deletePollById(Integer id) {
        String query = String.format("DELETE FROM polls WHERE id = %s", id);
        this.JdbService.getConnection().update(query);
    }
    
    public List<Map<String, Object>> replyLastPoll(Map<String, Object> lastPoll) throws Exception {
        List<Map<String, Object>> questions = (List<Map<String, Object>>)(lastPoll.get("questions"));
        return this.questionAnswerService.saveMany(questions);
    }
}
