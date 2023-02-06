package com.RemoteTokenMiddle.server.Auth;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RemoteTokenMiddle.server.Cookie.CookieService;
import com.RemoteTokenMiddle.server.Reponse.ResponseHandler;

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
    private final CookieService cookieService;

    @Autowired
    public AuthController(AuthService authService, CookieService cookieService) {
        this.authService = authService;
        this.cookieService = cookieService;
    }

    @PostMapping(path = "/sign-in")
    public ResponseEntity<Object> signIn(
            @RequestBody AuthRequest request, HttpServletResponse response) {

        AuthResponse authResponse = authService.signIn(request);
        Boolean authenticated = authResponse.getAuthenticated();
        if (authenticated) {
            String accessToken = authResponse.getAccessToken();
            String userName = authResponse.getUsername();
            Cookie accessTokenCookie = cookieService.createCookies("accessToken", accessToken, false, true, 3600 * 24);
            Cookie userNameCookie = cookieService.createCookies("username", userName, false, false, 3600 * 24);
            response.addCookie(accessTokenCookie);
            response.addCookie(userNameCookie);
            return ResponseHandler.reponseBuilder("Login Succesful", HttpStatus.OK, authResponse);
        } else {
            return ResponseHandler.reponseBuilder("Login Failed", HttpStatus.BAD_REQUEST, authResponse);
        }
    }

    @GetMapping(path = "/me")
    public ResponseEntity<Object> me(HttpServletRequest request) {
        String accessToken = cookieService.readCookie(request, "accessToken");
        String username = cookieService.readCookie(request, "username");
        if (accessToken.isEmpty()) {
            return ResponseHandler.reponseBuilder("No token found", HttpStatus.UNAUTHORIZED, null);
        } else {
            AuthResponse authResponse = new AuthResponse(true, username, accessToken);
            return ResponseHandler.reponseBuilder("User found", HttpStatus.OK, authResponse);
        }
    }

    @DeleteMapping(path = "/sign-out")
    public ResponseEntity<Object> signOut(HttpServletRequest request, HttpServletResponse response) {
        Cookie accessTokenCookie = cookieService.createCookies("accessToken", "", false, true, 0);
        Cookie userNameCookie = cookieService.createCookies("username", "", false, false, 0);
        response.addCookie(accessTokenCookie);
        response.addCookie(userNameCookie);
        return ResponseHandler.reponseBuilder("Logout Succesful", HttpStatus.OK, null);
    }
}