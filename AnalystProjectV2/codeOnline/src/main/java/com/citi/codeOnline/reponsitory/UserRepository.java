package com.citi.codeOnline.reponsitory;

import com.citi.codeOnline.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer>{
    public User findByUserName(String name);
}
