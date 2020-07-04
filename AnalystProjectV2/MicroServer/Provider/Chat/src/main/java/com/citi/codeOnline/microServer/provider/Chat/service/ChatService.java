package com.citi.codeOnline.microServer.provider.Chat.service;

import com.citi.codeOnline.microServer.provider.Chat.api.ChatServiceInte;
import com.citi.codeOnline.microServer.provider.Chat.entity.InterviewRoom;
import com.citi.codeOnline.microServer.provider.Chat.entity.User;
import com.citi.codeOnline.microServer.provider.Chat.message.WebMessage;
import com.citi.codeOnline.microServer.provider.Chat.repository.RoomRepository;
import com.citi.codeOnline.microServer.provider.Chat.repository.UserRepository;
import com.citi.codeOnline.microServer.provider.Chat.vo.RoomVO;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class ChatService implements ChatServiceInte {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoomRepository roomRepository;


    public WebMessage getAllUsersByRoomId(int roomId, HttpServletRequest request){

        HttpSession session = request.getSession();
        String sessionUserName = (String) session.getAttribute("userName");
        if(StringUtil.isNullOrEmpty(sessionUserName)) {
            return new WebMessage(false, "unlogined user !");
        }
        InterviewRoom room = this.roomRepository.getInterviewRoomByRoomId(roomId);
        if(room == null){
            return new WebMessage(false,"no such room exist! ");
        }
        List<User> users = room.getRoomUsers();
        if(users!=null && users.size() > 0){
            return new WebMessage(true,users,null);
        }
        else {
            return new WebMessage(false,"no opened room found");
        }
    }

    public WebMessage getRoomById(int roomId){
        InterviewRoom room = this.roomRepository.getInterviewRoomByRoomId(roomId);
        if(room == null){
            return new WebMessage(false,"no such room exist! ");
        }else {
            return new WebMessage(true,room);
        }
    }

    public WebMessage createRoom(RoomVO roomVO, HttpServletRequest request){
        InterviewRoom room = new InterviewRoom();
        System.out.println(roomVO.toString());
        System.out.println(userRepository);
        System.out.println(userRepository.findByUserName(roomVO.getUser()));
        room.setEditor(userRepository.findByUserName(roomVO.getUser()));
        room.setRoomUsers(userRepository.findUsersByUserNameIn(roomVO.getRoomUsers()));
        HttpSession session = request.getSession();
        String sessionUserName = (String) session.getAttribute("userName");
        if(StringUtil.isNullOrEmpty(sessionUserName)) {
            return new WebMessage(false, "unlogined user !");
        }
        room.setOwner(userRepository.findByUserName(sessionUserName));
        room.setOpen(true);
        InterviewRoom result = this.roomRepository.save(room);
         System.out.println(result.toString());
         return new WebMessage(true,result);
    }



}
