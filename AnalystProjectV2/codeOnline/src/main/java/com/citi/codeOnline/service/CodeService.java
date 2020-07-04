package com.citi.codeOnline.service;

import com.citi.codeOnline.message.WebMessage;
import com.citi.codeOnline.compiler.Java.JavaCompiler;
import com.citi.codeOnline.entityValue.Code;
import com.citi.codeOnline.microService.postApi;
import com.citi.codeOnline.microService.pythonRunnerService;

public class CodeService {
    public WebMessage runCodes(Code code){
        System.out.println("get code type"+code.getType());
        System.out.println("get code content"+code.getContent());
        WebMessage result = null;

        if("java".equals(code.getType())){
            result = this.javaRunner(code.getContent());
        }

        if("python".equals(code.getType())){
            result = this.pythonRunner(code.getContent());
        }
        return result;
    }

    public WebMessage pythonRunner(String codeStr){
        pythonRunnerService pa = new pythonRunnerService();
        return pa.runPython(codeStr);
    }

    public WebMessage javaRunner(String codeStr){
        JavaCompiler javaCompiler = new JavaCompiler();
        Object result = null;
        try {
            result = javaCompiler.run(codeStr);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new WebMessage(true,result,"test run");
    }
}
