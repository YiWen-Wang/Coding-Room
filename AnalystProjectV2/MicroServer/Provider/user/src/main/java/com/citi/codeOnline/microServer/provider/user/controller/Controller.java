package com.citi.codeOnline.microServer.provider.user.controller;

import com.citi.codeOnline.microServer.provider.user.entity.User;
import com.citi.codeOnline.microServer.provider.user.message.WebMessage;
import com.citi.codeOnline.microServer.provider.user.repository.UserRepository;
import com.citi.codeOnline.microServer.provider.user.repository.UserStatusRepository;
import com.citi.codeOnline.microServer.provider.user.service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/provider")
public class Controller {
    @GetMapping("/test")
    public String providerTest(){
        return "hello: user provider 8006";
    }

    @Autowired
    UserService userService;

    @PostMapping("/user/login")
    public WebMessage userLogin(@RequestBody User user, HttpServletRequest request){
        System.out.println(request.getSession().getId());
        return       userService.userLogin(user,request);
    }

    @PostMapping("/user/register")
    public WebMessage userRegister(@RequestBody User user, HttpServletRequest request){
        return userService.userRegister(user);
    }

    @GetMapping("/user/logout")
    public WebMessage userLogout(HttpServletRequest request){
        return userService.userLogOut(request);
    }

    @GetMapping("/user/checkLogin")
    public WebMessage userLoginCheck(HttpServletRequest request){
        return userService.userOnlineCheck(request);
    }

    @GetMapping("/user/Candidate/suggestion/{vagueName}")
    public WebMessage condidateSuggestion(@PathVariable("vagueName") String vagueName){
        return this.userService.getCondadidateSuggestion(vagueName);
    }

    @GetMapping("/user/Interviewer/suggestion/{vagueName}")
    public WebMessage interviewerSuggestion(@PathVariable("vagueName") String vagueName){
        return this.userService.getInterviewerSuggestion(vagueName);
    }
}
