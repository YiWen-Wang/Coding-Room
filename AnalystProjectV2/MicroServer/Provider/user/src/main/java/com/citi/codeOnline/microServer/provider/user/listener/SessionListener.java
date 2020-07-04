package com.citi.codeOnline.microServer.provider.user.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.util.HashSet;

@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {
    @Override
    public void sessionDestroyed(HttpSessionEvent event) throws ClassCastException {
        System.out.println("---sessionDestroyed----");
        HttpSession session = event.getSession();
        System.out.println("deletedSessionId: "+session.getId());
        System.out.println(session.getCreationTime());
        System.out.println(session.getLastAccessedTime());
        ServletContext application = session.getServletContext();
        HashSet sessions = (HashSet) application.getAttribute("sessions");
        // 销毁的session均从HashSet集中移除
        sessions.remove(session);
    }
}
