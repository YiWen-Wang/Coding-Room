package com.citi.codeOnline.microServer.provider.Chat.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketListenerDisconnect implements ApplicationListener<SessionDisconnectEvent> {
    @Override
    public void onApplicationEvent(SessionDisconnectEvent sessionDisconnectEvent) {
        System.out.println(sessionDisconnectEvent.getSessionId());
        System.out.println("user offline \n\n\n\n\n");
    }
}
