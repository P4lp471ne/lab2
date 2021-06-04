package com.oop.backend.service;

public interface GetTokenService {
    String getToken(String username, String password) throws Exception;
}
