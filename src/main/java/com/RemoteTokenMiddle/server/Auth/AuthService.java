package com.RemoteTokenMiddle.server.Auth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class AuthService {
    private static final String getAccessTokenForTMSRAURL = "http://192.168.2.203:8084/RegistrationAuthority/tmsra/restapi/getAccessTokenForTMSRA";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public AuthResponse signIn(AuthRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        RequestBody requestBody = RequestBody.create(JSON,
                "{\"userName\":\"" + username + "\",\"passWord\":\"" + password + "\"}");
        Request req = new Request.Builder()
                .url(getAccessTokenForTMSRAURL)
                .post(requestBody)
                .build();
        OkHttpClient client = new OkHttpClient();
        try (Response response = client.newCall(req).execute()) {
            String responseBody = response.body().string();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseBody);
            String accessToken = root.path("accessToken").asText();
            if (accessToken.isEmpty() == false)
                return new AuthResponse(true, username, accessToken);
            else
                return new AuthResponse(false, null, null);
        } catch (Exception e) {
            return new AuthResponse(false, null, null);
        }
    }

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

    public Cookie createCookies(String key, String value, Boolean isSecure , Boolean isHttpOnly, int maxAge) {
        Cookie cookie = new Cookie(key, value);
        cookie.setHttpOnly(isHttpOnly);
        cookie.setSecure(isSecure);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        return cookie;
    }
}
