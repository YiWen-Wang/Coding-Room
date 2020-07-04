package com.citi.codeOnline.controller;

import com.citi.codeOnline.entity.Sender;
import com.citi.codeOnline.message.RequestMessage;
import com.citi.codeOnline.service.MQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
public class MQTestController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    Sender senderMQ;




    /**
     * 聊天室（单聊+多聊）&&消息转发
     *
     * @param requestMessage
     * @throws Exception
     */
    @MessageMapping("/chatmq")
    public void messageHandling(RequestMessage requestMessage) throws Exception {
        String destination = "/topic/" + HtmlUtils.htmlEscape(requestMessage.getRoom());

        String room = HtmlUtils.htmlEscape(requestMessage.getRoom());//htmlEscape  转换为HTML转义字符表示
        String type = HtmlUtils.htmlEscape(requestMessage.getType());
        String content = HtmlUtils.htmlEscape(requestMessage.getContent());
        String userId = HtmlUtils.htmlEscape(requestMessage.getUserId());
        String questionId = HtmlUtils.htmlEscape(requestMessage.getQuestionId());
        String createTime = HtmlUtils.htmlEscape(requestMessage.getCreateTime());

        System.out.println(requestMessage.getRoom());
        System.out.println(content);

        messagingTemplate.convertAndSend(destination, requestMessage);
    }


}