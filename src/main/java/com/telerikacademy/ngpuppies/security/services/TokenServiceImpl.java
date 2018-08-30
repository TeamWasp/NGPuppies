package com.telerikacademy.ngpuppies.security.services;

import com.telerikacademy.ngpuppies.security.models.JwtTokenUtil;
import com.telerikacademy.ngpuppies.security.services.base.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static com.telerikacademy.ngpuppies.security.Constants.HEADER_STRING;
import static com.telerikacademy.ngpuppies.security.Constants.TOKEN_PREFIX;

@Service
public class TokenServiceImpl implements TokenService<HttpServletRequest> {
	
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	public TokenServiceImpl(JwtTokenUtil jwtTokenUtil) {
		this.jwtTokenUtil = jwtTokenUtil;
	}
	
	@Override
	public String getUsernameFromToken(HttpServletRequest req) {
		String header = req.getHeader(HEADER_STRING);
		String username = null;
		if (header.startsWith(TOKEN_PREFIX)) {
			String authToken = header.replace(TOKEN_PREFIX, "");
			try {
				username = jwtTokenUtil.getUsernameFromToken(authToken);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		} else {
		System.out.println("invalid token prefix");
		throw new NullPointerException();
		}
		return username;
	}
}
