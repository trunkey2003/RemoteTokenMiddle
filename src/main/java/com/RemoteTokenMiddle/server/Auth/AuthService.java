package com.RemoteTokenMiddle.server.Auth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.RemoteTokenMiddle.server.Request.SendRequestService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class AuthService {
    private static final String getAccessTokenForTMSRAURL = "http://192.168.2.203:8084/RegistrationAuthority/tmsra/restapi/getAccessTokenForTMSRA";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private final SendRequestService sendRequestService;

    @Autowired
    public AuthService(SendRequestService sendRequestService) {
        this.sendRequestService = sendRequestService;
    }

    public AuthResponse signIn(AuthRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        RequestBody requestBody = RequestBody.create(JSON,
                "{\"userName\":\"" + username + "\",\"passWord\":\"" + password + "\"}");
        try (Response response = sendRequestService.postRequest(requestBody, getAccessTokenForTMSRAURL)) {
            if (response == null) return new AuthResponse(false, null, null);
            String responseBody = response.body().string();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseBody);
            String accessToken = root.path("accessToken").asText();
            if (accessToken.isEmpty() == false)
                return new AuthResponse(true, username, accessToken);
            else
                return new AuthResponse(false, null, null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new AuthResponse(false, null, null);
        }
    }
}
