package com.citi.codeOnline.microServer.provider.Chat.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@Component
public class WebSocketListenerConnect implements ApplicationListener<SessionConnectedEvent> {

    @Override
    public void onApplicationEvent(SessionConnectedEvent sessionConnectedEvent) {
       System.out.println(sessionConnectedEvent.getUser());
       sessionConnectedEvent.toString();
    }
}
