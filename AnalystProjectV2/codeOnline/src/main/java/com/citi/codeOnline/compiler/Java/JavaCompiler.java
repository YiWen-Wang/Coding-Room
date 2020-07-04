package com.citi.codeOnline.compiler.Java;

import com.citi.codeOnline.compiler.Compiler;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.security.AccessControlContext;

public class JavaCompiler implements Compiler {

    @Override
    public Object run(String scriptText) {
        Binding binding = new Binding();
        GroovyShell shell = new GroovyShell(binding);
        Object value = null;
        try {
            value = shell.evaluate(scriptText);
        }catch (Exception e){
            value = e.toString();
        }
        return value;
    }
}
