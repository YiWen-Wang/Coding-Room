package com.citi.codeOnline.entity;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String context) { //注意因为是AmqpTemplate，所有这里只接受String,byte[],Seriz..
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }

}
