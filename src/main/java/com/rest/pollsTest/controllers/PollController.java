/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.pollsTest.controllers;

import com.google.gson.Gson;
import com.rest.pollsTest.models.Poll;
import com.rest.pollsTest.models.QuestionAnswer;
import com.rest.pollsTest.services.PollService;
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
public class PollController {

    private final PollService pollService;

    public PollController() {
        this.pollService = new PollService();
    }

    @RequestMapping(value = "/poll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getPolls() {
        List polls = this.pollService.getPolls();
        return new Gson().toJson(polls);
    }

    
    @RequestMapping(value = "/poll/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getPollById(@PathVariable("id") int id) {
        System.out.println(id);
        Poll poll = this.pollService.getPollById(id);
        return new Gson().toJson(poll);
    }
    
    
    @RequestMapping(value = "/poll/last", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getLastPoll() {
        Poll lastPoll = this.pollService.getLastPoll();
        return new Gson().toJson(lastPoll);
    }
    
    
    @RequestMapping(value = "/poll/responses/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getResponses(@PathVariable("id") int id) {
        List<QuestionAnswer> responses = this.pollService.getResponsesById(id);
        return new Gson().toJson(responses);
    }
}
