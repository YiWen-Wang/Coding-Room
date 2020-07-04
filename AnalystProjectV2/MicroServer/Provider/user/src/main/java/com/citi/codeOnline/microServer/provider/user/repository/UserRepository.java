package com.citi.codeOnline.microServer.provider.user.repository;


import com.citi.codeOnline.microServer.provider.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer>{
    public User findByUserName(String name);

    @Query(value = "select * from user where user_Type <> 0 and user_Type <> 1 and user_Name like %?1% limit 10 ", nativeQuery = true)
    public List<User> getCondidateSuggestion(String vagueName);


    @Query(value = "select * from user where user_Type in (0,1) and user_Name like %?1% limit 10", nativeQuery = true)
    public List<User> getInterviewerSuggestion(String vagueName);
}
