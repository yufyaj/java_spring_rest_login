package com.test.java_spring_rest_login.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.java_spring_rest_login.model.User;
import com.test.java_spring_rest_login.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@RestController
public class LoginController{
    @Autowired
    UserService userService;

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String root() {
        return "login";
    }

    @RequestMapping(value="login", method=RequestMethod.POST)
    public void login(@RequestBody User reqUser, HttpServletResponse res) {
        try{
            String sessionId = userService.doLogin(reqUser);
            res.setStatus(HttpStatus.OK.value());
            res.addCookie(new Cookie("sessionId", sessionId));
        } catch (Exception e) {
            res.setStatus(HttpStatus.BAD_REQUEST.value());
        }
    }
}