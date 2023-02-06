package com.RemoteTokenMiddle.server.Certificate;

import java.lang.reflect.Array;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.RemoteTokenMiddle.server.Auth.AuthService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CertificateService {
    private final String getCertificateAuthorityForTMSRAURL = "http://192.168.2.203:8084/RegistrationAuthority/tmsra/restapi/getCertificateAuthorityForTMSRA";
    private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private final OkHttpClient client = new OkHttpClient();
    private final AuthService authService;

    @Autowired
    public CertificateService(AuthService authService) {
        this.authService = authService;
    }

    public List<JsonNode> getCertificateAuthorityForTMSRA(HttpServletRequest request) {
        String language = "0";
        RequestBody requestBody = RequestBody.create(JSON,
                "{\"language\":\"" + language + "\"}");
        String username = authService.readCookie(request, "username");
        String accessToken = authService.readCookie(request, "accessToken");
        Request req = new Request.Builder()
                .url(getCertificateAuthorityForTMSRAURL)
                .addHeader("userName", username)
                .addHeader("Authorization", accessToken)
                .post(requestBody)
                .build();
        try (Response response = client.newCall(req).execute()) {
            String responseBody = response.body().string();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseBody);
            List<JsonNode> certificateAuthorityInfo = root.findValues("certificateAuthorityInfo");
            if (certificateAuthorityInfo.size() == 0) {
                return null;
            };
            return certificateAuthorityInfo;
        } catch (Exception e) {
            return null;
        }
    }
}
