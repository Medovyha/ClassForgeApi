package com.classforge.api.ClassForgeAPI.web.controllers;

import com.classforge.api.ClassForgeAPI.dao.User;
import com.classforge.api.ClassForgeAPI.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value="/api/v1/user/{id:\\d+}")
    public User getUser(@PathVariable Integer id){
        System.out.println("UserController.getUser");
        return userService.getUserById(id);}


    @GetMapping(value="login")
    public String login(){
        return "login";
    }
}
