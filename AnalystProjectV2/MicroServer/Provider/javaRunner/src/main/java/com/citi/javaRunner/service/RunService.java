package com.citi.javaRunner.service;

import com.citi.javaRunner.message.WebMessage;
import io.netty.util.internal.StringUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class RunService {

    private String errorPath = "D:\\AnalystProjectV2\\MicroServer\\Provider\\javaRunner\\source\\logs\\";
    private String outPath = "D:\\AnalystProjectV2\\MicroServer\\Provider\\javaRunner\\source\\logs\\";
    private String pathStr = "D:\\AnalystProjectV2\\MicroServer\\Provider\\javaRunner\\source\\";
    private String pathStrOri = "D:\\AnalystProjectV2\\MicroServer\\Provider\\javaRunner\\source\\";
    private String runClass = null;

    public WebMessage runCode(String codes, HttpServletRequest httpServletRequest) throws IOException {
        if(!this.setBasicRunEnv(httpServletRequest)){
            return new WebMessage(false,"Cannot get login info");
        };
        this.sysErrInterceptor();
        this.sysOutInterceptor();
        this.saveFiles(codes,pathStr);
        WebMessage result =  this.runCore(pathStr);
        return result;
    }

    public void saveFiles( String codes, String pathstr) throws IOException {
        byte[] bytes = codes.getBytes();
        Path path = Paths.get(pathstr);
        if(Files.notExists(path)){
            Files.createFile(path);
        }else {
            Files.delete(path);
            Files.createFile(path);
        }
        Files.write(path,bytes);
    }

    public WebMessage runCore(String pathStr) throws IOException {
        try {
            JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
            int result = javaCompiler.run(null, null, null, pathStr);
            if(result!=0){
                throw new Exception("Compiler error");
            }
            else {
                URL[] urls = new URL[]{new URL("file:/" + this.pathStrOri)};
                URLClassLoader classLoader = new URLClassLoader(urls);
                Class c = classLoader.loadClass(this.runClass);
                Method method = c.getDeclaredMethod("main", String[].class);
                method.invoke(null, (Object) new String[]{""});
             }
        }catch (Exception e){
            System.out.println(e.toString());
        }finally {
            String[] finalResult = new String[2];
            finalResult[0] = this.getOutMsg();
            finalResult[1] = this.getErrMsg();
            return new WebMessage(true,finalResult,"");
        }

    }

    public void sysOutInterceptor() throws IOException {
        Path path = Paths.get(this.outPath);
        if(Files.notExists(path)){
            Files.createFile(path);
        }else {
            Files.write(path,"".getBytes());
      //      Files.delete(path);
      //      Files.createFile(path);
        }
        PrintStream out = null;
        try {
            out = new PrintStream(this.outPath);
        }catch(Exception e) {
            e.printStackTrace();
        }
        System.setOut(out);
    }

    public void sysErrInterceptor() throws IOException {
        Path path = Paths.get(this.errorPath);
        if(Files.notExists(path)){
            Files.createFile(path);
        }else {
            Files.write(path,"".getBytes());
        //    Files.delete(path);
        //    Files.createFile(path);
        }
        PrintStream err = null;
        try {
            err = new PrintStream(this.errorPath);
        }catch(Exception e) {
            e.printStackTrace();
        }
        System.setErr(err);
    }

    public String getOutMsg() throws IOException {
        Path path = Paths.get(this.outPath);
        List<String> strs = Files.readAllLines(path);
        StringBuffer result = new StringBuffer();
        strs.forEach(item->{
            result.append(item);
            result.append("\n");
        });
        return result.toString();
    }

    public String getErrMsg() throws IOException {
        Path path = Paths.get(this.errorPath);
        List<String> strs = Files.readAllLines(path);
        StringBuffer result = new StringBuffer();
        strs.forEach(item->{
            result.append(item);
            result.append("\n");
        });
        return result.toString();
    }

    private boolean setBasicRunEnv(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        String sessionUserName = (String) session.getAttribute("userName");
        if(!StringUtil.isNullOrEmpty(sessionUserName)) {
            if(!this.errorPath.contains("_err.txt")) {
                this.errorPath = this.errorPath + sessionUserName + "_" + "err.txt";
                this.outPath = this.outPath + sessionUserName + "_" + "print.txt";
            }
            if(pathStr.equals(pathStrOri)) {
                this.pathStr = pathStr + sessionUserName + ".java";
            }
            this.runClass = sessionUserName;
            return true;
        }else {
            return false;
        }

    }

}
