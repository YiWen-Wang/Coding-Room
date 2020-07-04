package com.citi.codeOnline.microServer.provider.Chat.message;

public class MqRequestMessage {
    private String room;//频道号
    private String type;//消息类型('1':客户端到服务端   '2'：客户端到服务端)
    private String content;//消息内容（即答案）
    private String sender;//用户id

    public MqRequestMessage() {
    }

    public MqRequestMessage(String room, String type, String content, String userId, String questionId, String createTime) {
        this.room = room;
        this.type = type;
        this.content = content;
        this.sender = userId;
    }


    public String getRoom() {
        return room;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
