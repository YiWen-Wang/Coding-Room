package com.citi.codeOnline.session.gateway.config;


import com.citi.codeOnline.session.gateway.handle.AuthenticationFaillHandler;
import com.citi.codeOnline.session.gateway.handle.AuthenticationSuccessHandler;
import com.citi.codeOnline.session.gateway.handle.CustomHttpBasicServerAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SecurityConfig {


    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFaillHandler authenticationFaillHandler;
    @Autowired
    private CustomHttpBasicServerAuthenticationEntryPoint customHttpBasicServerAuthenticationEntryPoint;


    //security exclude list
    private static final String[] excludedAuthPages = {
            "/user/provider/user/login",
            "/user/provider/user/checkLogin"
            //, "/api/socket/**"
    };

    @Bean
    SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) throws Exception {
        http
                .authorizeExchange()
                .pathMatchers(excludedAuthPages).permitAll()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .anyExchange().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin().loginPage("/auth/login")
                .authenticationSuccessHandler(authenticationSuccessHandler)
                .authenticationFailureHandler(authenticationFaillHandler)
                .and().exceptionHandling().authenticationEntryPoint(customHttpBasicServerAuthenticationEntryPoint)  //基于http的接口请求鉴权失败
                .and() .csrf().disable()
                .logout().disable();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return  NoOpPasswordEncoder.getInstance();
    }


}
