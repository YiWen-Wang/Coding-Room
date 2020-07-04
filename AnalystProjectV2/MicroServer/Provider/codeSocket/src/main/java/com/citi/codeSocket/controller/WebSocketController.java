package com.citi.codeSocket.controller;


import com.citi.codeSocket.message.MqRequestMessage;
import com.citi.codeSocket.message.MqResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;


@RestController
public class WebSocketController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // type = 1 :  chat message

    /*
    @Autowired
      Sender senderMQ;
    */
    /**聊天室（单聊+多聊）&&消息转发
     * @param requestMessage
     * @throws Exception
     */
    @MessageMapping("/chat")
    public void messageHandling(MqRequestMessage requestMessage ) throws Exception {
        String destination = "/topic/" + requestMessage.getRoom();
        String language = requestMessage.getLanguage();  //htmlEscape  转换为HTML转义字符表示
        String type = requestMessage.getType();
        String content = requestMessage.getContent();
        MqResponseMessage response = new MqResponseMessage(language, type, content);
        messagingTemplate.convertAndSend(destination, response);
    }


    /*
    @MessageMapping("/chatAll")
    public void messageHandlingAll(MqRequestMessage requestMessage) throws Exception {
        String destination = "/all";
        String sender = HtmlUtils.htmlEscape(requestMessage.getSender());  //htmlEscape  转换为HTML转义字符表示
        String type = HtmlUtils.htmlEscape(requestMessage.getType());
        String content = HtmlUtils.htmlEscape(requestMessage.getContent());
        MqResponseMessage response = new MqResponseMessage(sender, type, content);
        messagingTemplate.convertAndSend(destination, response);
    }
    */



}
