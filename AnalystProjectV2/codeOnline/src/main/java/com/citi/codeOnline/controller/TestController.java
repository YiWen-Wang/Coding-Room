package com.citi.codeOnline.controller;

import com.citi.codeOnline.service.MQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    MQService commonService;


    @GetMapping("/test/testAsync")
    public String testAsync() {
        commonService.testAsync();
        return "http请求已结束";
    }

}
