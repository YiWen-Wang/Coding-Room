package com.citi.codeOnline.microServer.provider.Chat.service;

import com.citi.codeOnline.microServer.provider.Chat.entity.InterviewRoom;
import com.citi.codeOnline.microServer.provider.Chat.entity.User;
import com.citi.codeOnline.microServer.provider.Chat.message.WebMessage;
import com.citi.codeOnline.microServer.provider.Chat.repository.UserRepository;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public WebMessage getAllRoomsByUserName(HttpServletRequest request){

        HttpSession session = request.getSession();
        String sessionUserName = (String) session.getAttribute("userName");
        if(StringUtil.isNullOrEmpty(sessionUserName)) {
            return new WebMessage(false, "unlogined user !");
        }

        User user= this.userRepository.findByUserName(sessionUserName);
        List<InterviewRoom> rooms = user.getRooms();
        if(rooms!=null)
        {
            rooms.stream().filter(InterviewRoom::isOpen).collect(Collectors.toList());
        }
        if(rooms.size() > 0){
            return new WebMessage(true,rooms,null);
        }
        else {
            return new WebMessage(false,"no opened room found");
        }
    }
}
