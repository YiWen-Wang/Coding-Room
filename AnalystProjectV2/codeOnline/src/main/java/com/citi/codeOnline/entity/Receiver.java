package com.citi.codeOnline.entity;

import com.citi.codeOnline.message.MqRequestMessage;
import com.citi.codeOnline.tools.JsonUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
@RabbitListener(queues = "hello")
public class Receiver {


    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @RabbitHandler
    public void process(String context) throws IOException {
        System.out.println("Receiver : " + context);

        MqRequestMessage mqTask = new MqRequestMessage();
        BeanUtils.copyProperties(JsonUtils.jsonToObject(context, MqRequestMessage.class), mqTask);
        return;
/*
        if (Objects.equals(mqTask.getType(), "2")) {
            String destination = "/topic/" + mqTask.getRoom();

            messagingTemplate.convertAndSend(destination, mqTask);
        }*/

    }

}