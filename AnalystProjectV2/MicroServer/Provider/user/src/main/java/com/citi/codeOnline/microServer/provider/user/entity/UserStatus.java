package com.citi.codeOnline.microServer.provider.user.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userStatus")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class UserStatus {

    @Id
    private String userName;
    private int userStatus;

    public UserStatus() {
    }

    public UserStatus(String userName, int userStatus) {
    this.userName = userName;
    this.userStatus = userStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }
}
