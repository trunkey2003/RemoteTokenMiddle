package com.RemoteTokenMiddle.server.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthRepository authRepository;

    @Autowired
    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public AuthResponse login(AuthRequest request) {
        return authRepository.checkLogin(request.getUsername(), request.getPassword());
    }
}
