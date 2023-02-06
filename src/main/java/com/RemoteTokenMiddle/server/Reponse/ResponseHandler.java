package com.RemoteTokenMiddle.server.Reponse;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
    public static ResponseEntity<Object> reponseBuilder(String message, HttpStatus httpStatus, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("httpStatus", httpStatus.value());
        response.put("data", data);
        return new ResponseEntity<Object>(response,httpStatus);
    }
}
