package com.rest.pollsTest.models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Julian Olarte Torres
 */
public class QuestionAnswer {
    private Integer id;
    private Integer answerId;
    private Integer pollId; 
    private Integer questionId;
    private String sessionId;
    private String createdAt;
    private String updatedAt;

    public QuestionAnswer(Integer id, Integer answerId, Integer pollId, Integer questionId, String sessionId, String createdAt, String updatedAt) {
        this.id = id;
        this.answerId = answerId;
        this.pollId = pollId;
        this.questionId = questionId;
        this.sessionId = sessionId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getPollId() {
        return pollId;
    }

    public void setPollId(Integer pollId) {
        this.pollId = pollId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
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
    
}
