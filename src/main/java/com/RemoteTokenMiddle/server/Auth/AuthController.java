package com.RemoteTokenMiddle.server.Auth;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/sign-in")
    public ResponseEntity<AuthResponse> authenticate(
            @RequestBody AuthRequest request, HttpServletResponse response) {
        
        AuthResponse authResponse = authService.login(request);
        Boolean authenticated = authResponse.getAuthenticated();
        if (authenticated) {
            Cookie cookie = new Cookie("username", request.getUsername());
            cookie.setHttpOnly(true);
            cookie.setSecure(false);
            cookie.setPath("/");
            cookie.setMaxAge(3600);
            response.addCookie(cookie);
            return new ResponseEntity<>(authResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
        }
    }
}