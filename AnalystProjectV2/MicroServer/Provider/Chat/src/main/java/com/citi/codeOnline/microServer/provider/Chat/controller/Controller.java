package com.citi.codeOnline.microServer.provider.Chat.controller;

import com.citi.codeOnline.microServer.provider.Chat.message.WebMessage;
import com.citi.codeOnline.microServer.provider.Chat.service.ChatService;
import com.citi.codeOnline.microServer.provider.Chat.service.UserService;
import com.citi.codeOnline.microServer.provider.Chat.vo.RoomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/provider")
public class Controller {

    @Autowired
    ChatService chatService;

    @Autowired
    UserService userService;


    @GetMapping("/room/getOnlineAll/{roomId}")
    public WebMessage getAllOnlineUserByRoomId(@PathVariable("roomId") int roomId, HttpServletRequest request){
        return chatService.getAllUsersByRoomId(roomId,request);
    }

    @GetMapping("/user/rooms")
    public WebMessage getAllOpenRoomsByUserName(HttpServletRequest request){
        String userName = "yw98506";
        return userService.getAllRoomsByUserName(request);
    }

    @GetMapping("/room/getRoom/{roomId}")
    public WebMessage getRoomByRoomId(@PathVariable("roomId") int roomId, HttpServletRequest request){
        return chatService.getRoomById(roomId);
    }

    @PostMapping("/room/createRoom")
    public WebMessage createRoom(@RequestBody RoomVO room , HttpServletRequest request){
        return  chatService.createRoom(room,request);
    }



}
