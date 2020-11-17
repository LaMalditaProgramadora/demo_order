package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@ControllerAdvice(basePackages={"com.example.demo.controller"})
public class UserControllerAdvice {
    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public User userInSession(String username) {
    User user = userService.findByUsername(username);
        return user;
    }
}
