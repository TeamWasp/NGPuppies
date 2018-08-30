package com.telerikacademy.ngpuppies.security.services.base;

public interface TokenService<T> {
	String getUsernameFromToken(T obj);
}
