package com.citi.codeOnline.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@Entity
@Table(name = "User")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class User{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int userId;
    private String userName;

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    private String userPassword;

    @ManyToOne(cascade=CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Type")
    private UserType userType;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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


}
