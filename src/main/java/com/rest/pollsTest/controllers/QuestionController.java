/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.pollsTest.controllers;

import com.google.gson.Gson;
import com.rest.pollsTest.models.Question;
import com.rest.pollsTest.services.QuestionService;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Julian Olarte Torres
 */
@RestController
public class QuestionController {

    private final QuestionService questionService;
    
    public QuestionController() {
        this.questionService = new QuestionService();
    }
    
    @RequestMapping(value = "/question", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getQuestions() {
        List polls = this.questionService.getQuestions();
        return new Gson().toJson(polls);
    }

    @RequestMapping(value = "/question/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getQuestionById(@PathVariable("id") int id) {
        System.out.println(id);
        Question poll = this.questionService.getQuestionById(id);
        return new Gson().toJson(poll);
    }
    
}
