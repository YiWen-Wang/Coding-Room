package com.citi.codeOnline.microServer.provider.user.repository;

import com.citi.codeOnline.microServer.provider.user.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStatusRepository extends JpaRepository<UserStatus,Integer> {

}
