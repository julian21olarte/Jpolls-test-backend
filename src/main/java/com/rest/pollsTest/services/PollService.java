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

/**
 *
 * @author Julian Olarte Torres
 */
public class PollService {
    private final JDBCService JdbService;
    private final QuestionAnswerService questionAnswerService;

    public PollService() {
        this.JdbService = new JDBCService();
        this.questionAnswerService = new QuestionAnswerService();
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
}
