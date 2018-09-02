package com.telerikacademy.ngpuppies.security;

import com.telerikacademy.ngpuppies.models.User;
import com.telerikacademy.ngpuppies.security.services.base.TokenService;
import com.telerikacademy.ngpuppies.services.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class FirstLoginInterceptor implements HandlerInterceptor {


    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    public FirstLoginInterceptor() {
    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws IOException {
        User user = userService.getByUsername(tokenService.getUsernameFromToken(req));
        if(user.isFirstLogin()){
            return false;
        }









        return true;
    }
}
