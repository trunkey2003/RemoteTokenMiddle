package com.RemoteTokenMiddle.server.Request;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RemoteTokenMiddle.server.Cookie.CookieService;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Service
public class SendRequestService {
    private static final OkHttpClient client = new OkHttpClient();
    private final CookieService cookieService;

    @Autowired
    public SendRequestService(CookieService cookieService) {
        this.cookieService = cookieService;
    }

    public Response postRequest(RequestBody requestBody, String url) {
        Request req = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        try (Response response = client.newCall(req).execute()) {
            // Have to create a new response body because the response body can't be read
            // after connection is closed
            String responseBodyString = response.body().string();
            ResponseBody body = ResponseBody.create(response.body().contentType(), responseBodyString);
            return response.newBuilder().body(body).build();
        } catch (Exception e) {
            return null;
        }
    };

    public Response postRequestAuth(RequestBody requestBody, String url, HttpServletRequest request) {
        String username = cookieService.readCookie(request, "username");
        String accessToken = cookieService.readCookie(request, "accessToken");
        Request req = new Request.Builder()
                .url(url)
                .addHeader("userName", username)
                .addHeader("Authorization", accessToken)
                .post(requestBody)
                .build();
        try (Response response = client.newCall(req).execute()) {
            // Have to create a new response body because the response body can't be read
            // after connection is closed
            String responseBodyString = response.body().string();
            ResponseBody body = ResponseBody.create(response.body().contentType(), responseBodyString);
            return response.newBuilder().body(body).build();
        } catch (Exception e) {
            return null;
        }
    };
}
