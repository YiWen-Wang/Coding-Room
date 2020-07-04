package com.citi.codeOnline.session.gateway.service;

import com.citi.codeOnline.session.gateway.entity.UserPO;
import com.citi.codeOnline.session.gateway.repository.UserPORepository;
import com.citi.codeOnline.session.gateway.utils.MD5Encoder;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class SecurityUserDetailsService implements ReactiveUserDetailsService {

    @Autowired
    UserPORepository userPORepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        UserPO userPO = userPORepository.findByUserName(username);
        System.out.println("get user name:" + username);
        userPO.toString();
        if(StringUtils.equals(userPO.getUserName(),username)){
            UserDetails user = User.withUsername(userPO.getUserName())
                  .password(MD5Encoder.encode(userPO.getUserPassword(),username))
                    .roles("admin").authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("admin"))
                    .build();
            return Mono.just(user);
        }
        else{
            return Mono.error(new UsernameNotFoundException("User Not Found"));

        }

    }



}
