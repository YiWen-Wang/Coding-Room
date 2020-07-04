package com.citi.codeOnline.service;

import com.citi.codeOnline.message.WebMessage;
import com.citi.codeOnline.entity.User;
import com.citi.codeOnline.entityValue.UserValue;
import com.citi.codeOnline.reponsitory.UserRepository;
import java.io.Serializable;

/*
* Security Strategy, do not transport user directly
* */
public class UserService implements Serializable {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public WebMessage getOneUser(Integer userId){
        try {
            User user = userRepository.getOne(userId);
            return new WebMessage(true,this.getUserValue(user));
        }catch (Exception e){
            return new WebMessage(false,"cannot find user");
        }
    }

    public WebMessage updateOneUser(User user){
        try {
            userRepository.save(user);
            return new WebMessage(true);
        }catch (Exception e){
            return new WebMessage(false,e.toString());
        }
    }

    public UserValue getUserValue(User user){
        UserValue userValue = new UserValue();
        userValue.setUserName(user.getUserName());
        userValue.setUserType(user.getUserType().getTypeName());
        return userValue;
    }

    public WebMessage<UserValue> userLogin(User inputUser){
        User user = userRepository.findByUserName(inputUser.getUserName());
        UserValue userValue = null;
        if(user == null){
            return new WebMessage(false,"cannot find user");
        }
        else{
            if(!user.getUserPassword().equals(inputUser.getUserPassword())){
                return new WebMessage(false,"wrong password");
            }
            else{
                return new WebMessage<UserValue>(true,this.getUserValue(user));
            }
        }
    }

}
