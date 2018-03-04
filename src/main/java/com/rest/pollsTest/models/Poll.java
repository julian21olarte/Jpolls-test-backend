/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.pollsTest.models;


import com.rest.pollsTest.services.QuestionService;
import java.util.List;

/**
 *
 * @author Julian Olarte Torres
 */
public class Poll {
    private Integer id;
    private String title;
    private String description;
    private String createdAt;
    private String updatedAt;
    private final List<Question> questions;

    public Poll(Integer id, String title, String description, String createdAt, String updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.questions = this.getQuestions(this.id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    
    private List<Question> getQuestions(Integer id) {
        QuestionService questionService = new QuestionService();
        return questionService.getQuestionsByPollId(id);
    }
    
}
