package com.citi.javaRunner.controller;

import com.citi.javaRunner.entity.Code;
import com.citi.javaRunner.message.WebMessage;
import com.citi.javaRunner.service.RunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/java")
public class RunController {
    @Autowired
    RunService runService;

    @PostMapping("/run")
    public WebMessage runJava(@RequestBody Code code, HttpServletRequest httpServletRequest) throws IOException {
        return this.runService.runCode(code.getCode(), httpServletRequest);
    }
}
