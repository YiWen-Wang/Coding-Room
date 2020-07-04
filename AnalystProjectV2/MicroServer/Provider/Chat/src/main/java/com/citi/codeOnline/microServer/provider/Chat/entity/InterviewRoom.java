package com.citi.codeOnline.microServer.provider.Chat.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="interview_room")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class InterviewRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "pk", strategy = "uuid2")
    private int roomId;

    private String roomName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="owner_name", referencedColumnName = "userName")
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="editor_name", referencedColumnName = "userName")
    private User editor;

    private boolean open;

    @ManyToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    @JoinTable(name = "interview_room_user_link",joinColumns = @JoinColumn(name="room_id",referencedColumnName = "roomId"),inverseJoinColumns = @JoinColumn(name = "user_name",referencedColumnName = "userName"))
    private List<User> roomUsers;

    public InterviewRoom() {
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getEditor() {
        return editor;
    }

    public void setEditor(User editor) {
        this.editor = editor;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }


    public List<User> getRoomUsers() {
        return roomUsers;
    }

    @JsonBackReference
    public void setRoomUsers(List<User> roomUsers) {
        this.roomUsers = roomUsers;
    }
}
