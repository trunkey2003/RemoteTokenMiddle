package com.RemoteTokenMiddle.server.Auth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class AuthService {
    private final String AUTH_URL = "http://192.168.2.203:8084/RegistrationAuthority/tmsra/restapi/getAccessTokenForTMSRA";

    public AuthResponse login(AuthRequest request) {

        String username = request.getUsername();
        String password = request.getPassword();
        RequestBody requestBody = new FormBody.Builder()
                .add("userName", username)
                .add("passWord", password)
                .build();
        Request req = new Request.Builder()
                .url(AUTH_URL)
                .post(requestBody)
                .build();
        OkHttpClient client = new OkHttpClient();
        try (Response response = client.newCall(req).execute()) {
            String responseBody = response.body().string();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseBody);
            String accessToken = root.path("access_token").asText();
            if (accessToken.isEmpty() == false)
                return new AuthResponse(true, username, accessToken, "1");
            else
                return new AuthResponse(false, null, null, "2");
        } catch (Exception e) {
            return new AuthResponse(false, null, null, "3");
        }
    }

    public String getJWTTokenFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String accessToken = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("access_token")) {
                accessToken = cookie.getValue();
            };
        };
        return accessToken;
    }
}
