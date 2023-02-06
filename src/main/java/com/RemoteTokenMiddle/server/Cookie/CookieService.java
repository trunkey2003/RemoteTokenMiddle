package com.RemoteTokenMiddle.server.Cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public class CookieService {
    public String readCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        String value = "";
        if (cookies == null)
            return "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(key)) {
                value = cookie.getValue();
            }
            ;
        }
        ;
        return value;
    };

    public Cookie createCookies(String key, String value, Boolean isSecure, Boolean isHttpOnly, int maxAge) {
        Cookie cookie = new Cookie(key, value);
        cookie.setHttpOnly(isHttpOnly);
        cookie.setSecure(isSecure);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        return cookie;
    }
}
