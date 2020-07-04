package com.citi.codeOnline.microServer.provider.Chat.vo;

import java.util.List;

public class RoomVO {
    public String user;
    public List<String> roomUsers;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<String> getRoomUsers() {
        return roomUsers;
    }

    public void setRoomUsers(List<String> roomUsers) {
        this.roomUsers = roomUsers;
    }

    @Override
    public String toString() {
        return "RoomVO{" +
                "user='" + user + '\'' +
                ", roomUsers=" + roomUsers +
                '}';
    }
}
