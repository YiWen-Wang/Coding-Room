package com.citi.codeOnline.microServer.provider.Chat.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "User")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    private String userName;

    @OneToOne
    @JoinColumn(name = "user_Name", referencedColumnName = "userName")
    private UserStatus userStatus;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Type", referencedColumnName = "typeId")
    private UserType userType;

    @ManyToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    @JoinTable(name = "interview_room_user_link",joinColumns = @JoinColumn(name="user_name",referencedColumnName = "userName"),inverseJoinColumns = @JoinColumn(name = "room_id",referencedColumnName = "roomId"))
    private List<InterviewRoom> rooms;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public List<InterviewRoom> getRooms() {
        return rooms;
    }

    @JsonBackReference
    public void setRooms(List<InterviewRoom> rooms) {
        this.rooms = rooms;
    }
}
