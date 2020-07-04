package com.citi.codeOnline.controller;

import com.citi.codeOnline.message.WebMessage;
import com.citi.codeOnline.entityValue.Code;
import com.citi.codeOnline.service.CodeService;
import org.springframework.web.bind.annotation.*;

@RestController
public class CodeController {

    @PostMapping("/code/run")
    public WebMessage userLogin(@RequestBody Code code){
        return new CodeService().runCodes(code);
    }
}
