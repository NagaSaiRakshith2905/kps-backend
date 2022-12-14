package com.capgemini.node.controller;

import com.capgemini.node.model.UserRequest;
import com.capgemini.node.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class nodeController {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
    @Autowired
    private RestTemplate restTemplate;

    static final String STUDENT_URL_MS = "localhost:8080/api/user/";
    @PostMapping(path = "register/")
    public String registerUser(@RequestBody UserRequest user){
        return restTemplate.postForObject(STUDENT_URL_MS,user,String.class);
    }

    @GetMapping(path = "get-by-username/")
    public UserResponse getUserByUserName(@RequestParam(value = "username")String username){
        return restTemplate.exchange(STUDENT_URL_MS, HttpMethod.GET,null, UserResponse.class).getBody();
    }

}
