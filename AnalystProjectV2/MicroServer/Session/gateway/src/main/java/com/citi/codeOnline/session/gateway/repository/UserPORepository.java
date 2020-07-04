package com.citi.codeOnline.session.gateway.repository;

import com.citi.codeOnline.session.gateway.entity.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPORepository extends JpaRepository<UserPO,Integer>{
        public UserPO findByUserName(String name);

}
