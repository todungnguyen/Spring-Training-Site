package com.todungnguyen.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todungnguyen.model.User;

@RestController
public class MainController {

    @GetMapping("/login") 
    public String getLogin(@RequestBody User user) {
        return "login";
    }
}
