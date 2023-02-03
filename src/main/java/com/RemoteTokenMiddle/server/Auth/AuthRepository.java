package com.RemoteTokenMiddle.server.Auth;

import org.springframework.stereotype.Repository;

@Repository
public class AuthRepository {
    public AuthResponse checkLogin(String username, String password){
        if (username.equals("admin") && password.equals("admin")) {
            return new AuthResponse(true);
        } else {
            return new AuthResponse(false);
        }
    }
}
