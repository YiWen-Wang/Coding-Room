package com.citi.codeOnline.microServer.provider.user.entityValue;

import java.io.Serializable;

public class UserValue implements Serializable {

    private static final long serialVersionUID = 1L;
    private String userName;
    private String userType;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}