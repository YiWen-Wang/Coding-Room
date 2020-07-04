package com.citi.codeOnline.microServer.provider.user.service.UserService;


import com.citi.codeOnline.microServer.provider.user.entity.User;
import com.citi.codeOnline.microServer.provider.user.entity.UserStatus;
import com.citi.codeOnline.microServer.provider.user.entityValue.UserValue;
import com.citi.codeOnline.microServer.provider.user.message.WebMessage;
import com.citi.codeOnline.microServer.provider.user.repository.UserRepository;
import com.citi.codeOnline.microServer.provider.user.repository.UserStatusRepository;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * Security Strategy, do not transport user directly
 * */
@Service
public class UserService implements Serializable {


    @Autowired
    UserRepository userRepository;

    @Autowired
    UserStatusRepository userStatusRepository;


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

    public WebMessage<UserValue> userLogin(User inputUser, HttpServletRequest request){
        System.out.println("getname:"+inputUser.getUserName());
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
                HttpSession session = request.getSession();
                String userName = (String) session.getAttribute("userName");
                if(StringUtil.isNullOrEmpty(userName)){
                    session.setAttribute("userName", user.getUserName());
                }
                this.userStatusRepository.saveAndFlush(new UserStatus(inputUser.getUserName(),1));
                return new WebMessage<UserValue>(true,this.getUserValue(user));
            }
        }
    }

    public WebMessage userOnlineCheck(HttpServletRequest request){
        HttpSession session = request.getSession();
        String sessionUserName = (String) session.getAttribute("userName");
        if(!StringUtil.isNullOrEmpty(sessionUserName)){
            User user = userRepository.findByUserName(sessionUserName);
            if(user != null){
                return new WebMessage<UserValue>(true,this.getUserValue(user));
            }
            else{
                return new WebMessage(false,"unMatched user name");
            }
        }else {
            return new WebMessage(false,"please log in");
        }
    }

    public WebMessage userLogOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        String sessionUserName = (String) session.getAttribute("userName");
        if(!StringUtil.isNullOrEmpty(sessionUserName)) {
                session.removeAttribute("userName");
            this.userStatusRepository.saveAndFlush(new UserStatus(sessionUserName,0));
                return new WebMessage(true);

        }
        else{
            return new WebMessage(false,"User still not log in");
        }
    }

    public WebMessage userRegister(User inputUser) {
        if(!userValidation(inputUser)){
            return new WebMessage(false,"Illegal user");
        }
        if (userRepository.findByUserName(inputUser.getUserName()) != null) {
            return new WebMessage(false, "User Already exist");
        }
        try {
            userRepository.save(inputUser);
            return new WebMessage(true);
        }catch (Exception e){
            return new WebMessage(false,"server internal error !");
        }
    }

    private boolean userValidation(User user){
        if(user == null){
            return false;
        }
        if(user.getUserName() !=null && user.getUserPassword() !=null && user.getUserType() != null )
        {
            return true;
        }
        return false;
    }

    public WebMessage getCondadidateSuggestion(String vagueName){
        List<User> users = this.userRepository.getCondidateSuggestion(vagueName);
        List<UserValue> userVOs = new ArrayList<>();
        for(User user:users){
            userVOs.add(this.getUserValue(user));
        }
        return new WebMessage(true,userVOs);

    }

    public WebMessage getInterviewerSuggestion(String vagueName){
        List<User> users = this.userRepository.getInterviewerSuggestion(vagueName);
        List<UserValue> userVOs = new ArrayList<>();
        for(User user:users){
            userVOs.add(this.getUserValue(user));
        }
        return new WebMessage(true,userVOs);
    }




}
