/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.pollsTest.models;

/**
 *
 * @author Julian Olarte Torres
 */
public class Answer {
    
    private Integer id;
    private Integer questionId;
    private String description;
    private String createdAt;
    private String updatedAt;

    public Answer(Integer id, Integer questionId, String description, String createdAt, String updatedAt) {
        this.id = id;
        this.questionId = questionId;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
