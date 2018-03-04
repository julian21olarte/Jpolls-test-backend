/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.pollsTest.models;

import com.rest.pollsTest.services.AnswerService;
import java.util.List;

/**
 *
 * @author Julian Olarte Torres
 */
public class Question {
    private Integer id;
    private String description;
    private Integer pollId;
    private String createdAt;
    private String updatedAt;
    
    private final List<Answer> answers;

    public Question(Integer id, String description, Integer pollId, String createdAt, String updatedAt) {
        this.id = id;
        this.description = description;
        this.pollId = pollId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        
        this.answers = this.getAnswers(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPollId() {
        return pollId;
    }

    public void setPollId(Integer pollId) {
        this.pollId = pollId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    
    private List<Answer> getAnswers(Integer id) {
        AnswerService answerSwervice = new AnswerService();
        return answerSwervice.getAnswersByQuestionId(id);
    }
}
