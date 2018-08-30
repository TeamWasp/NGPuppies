package com.telerikacademy.ngpuppies.controllers;

import com.telerikacademy.ngpuppies.security.models.AuthToken;
import com.telerikacademy.ngpuppies.security.models.LoginUser;
import com.telerikacademy.ngpuppies.models.User;
import com.telerikacademy.ngpuppies.repositories.UserSqlRepository;
import com.telerikacademy.ngpuppies.security.models.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;


@RestController
@RequestMapping("/api")
public class AuthenticationController {
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private UserSqlRepository userSqlRepository;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserSqlRepository userSqlRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userSqlRepository = userSqlRepository;
    }
    @PostMapping("/login")
    public ResponseEntity register(@RequestBody LoginUser loginUser) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final User user = userSqlRepository.getByUsername(loginUser.getUsername());
        final String token = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(new AuthToken(token, user.getRole().getName().toString()));
    }
}
