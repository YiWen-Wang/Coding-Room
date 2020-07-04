package com.citi.codeOnline.microService;


import com.citi.codeOnline.message.WebMessage;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import sun.plugin2.message.Message;

public class pythonRunnerService {

    public WebMessage runPython(String codeStr){
        //请求路径
        String url = "http://localhost:5000/test";
        System.out.print("come here  !!!! \n\n\n");
        //使用Restemplate来发送HTTP请求
        RestTemplate restTemplate = new RestTemplate();
        LinkedMultiValueMap body = new LinkedMultiValueMap();
        body.add("code",codeStr);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity httpEntity = new HttpEntity(body, headers);
        try
        {
            ResponseEntity<String> strbody = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
            //解析返回的数据
            return new WebMessage(true,strbody.getBody(),null);
        }catch(Exception e)
        {
            return new WebMessage(false,null,e.toString());
        }
    }

}
