package com.citi.codeOnline.controller;

import com.citi.codeOnline.message.WebMessage;
import com.citi.codeOnline.entity.User;
import com.citi.codeOnline.reponsitory.UserRepository;
import com.citi.codeOnline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user/{id}")
    public WebMessage getUser(@PathVariable("id") Integer id){
        return new UserService(userRepository).getOneUser(id);
    }

    @PostMapping("/user/login")
    public WebMessage userLogin(@RequestBody User user){
        return new UserService(userRepository).userLogin(user);
    }
}
