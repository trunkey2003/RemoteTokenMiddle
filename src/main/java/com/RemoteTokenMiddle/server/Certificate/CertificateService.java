package com.RemoteTokenMiddle.server.Certificate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.RemoteTokenMiddle.server.Request.SendRequestService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CertificateService {
    private final String baseURL = "http://192.168.2.203:8084";

    private final String getCertificateAuthorityForTMSRAURL = baseURL
            + "/RegistrationAuthority/tmsra/restapi/getCertificateAuthorityForTMSRA";
    private final String getCertificatePurposeForTMSRAURL = baseURL
            + "/RegistrationAuthority/tmsra/restapi/getCertificatePurposeForTMSRA";
    private final String getCertificateProfileForTMSRAURL = baseURL
            + "/RegistrationAuthority/tmsra/restapi/getCertificateProfileForTMSRA";
    private final String getCertificateComponentForTMSRAURL = baseURL
            + "/RegistrationAuthority/tmsra/restapi/getCertificateComponentForTMSRA";

    private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private final SendRequestService sendRequestService;
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public CertificateService(SendRequestService sendRequestService) {
        this.sendRequestService = sendRequestService;
    }

    public JsonNode getCertificateAuthorityForTMSRA(HttpServletRequest request, String language) {
        Map<String, String> jsonObject = new HashMap<>();
        jsonObject.put("language", language);
        try {
            String json = mapper.writeValueAsString(jsonObject);
            RequestBody requestBody = RequestBody.create(JSON, json);
            Response response = sendRequestService.postRequestAuth(requestBody, getCertificateAuthorityForTMSRAURL,
                request); 
            String responseBody = response.body().string();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseBody);
            List<JsonNode> certificateAuthorityInfo = root.findValues("certificateAuthorityInfo");
            if (certificateAuthorityInfo.size() == 0) {
                return mapper.valueToTree("[]");
            }
            return certificateAuthorityInfo.get(0);
        } catch (Exception e) {
            return null;
        }
    }

    public JsonNode getCertificatePurposeForTMSRA(HttpServletRequest request, String language,
            String certificateAuthorityCode) {
        Map<String, String> jsonObject = new HashMap<>();
        jsonObject.put("language", language);
        jsonObject.put("certificateAuthorityCode", certificateAuthorityCode);
        try {
            String json = mapper.writeValueAsString(jsonObject);
            RequestBody requestBody = RequestBody.create(JSON, json);
            Response response = sendRequestService.postRequestAuth(requestBody, getCertificatePurposeForTMSRAURL,
                    request);
            String responseBody = response.body().string();
            JsonNode root = mapper.readTree(responseBody);
            List<JsonNode> certificatePurposeInfo = root.findValues("certificatePurposeInfo");
            if (certificatePurposeInfo.size() == 0) {
                return mapper.valueToTree("[]");
            }
            return certificatePurposeInfo.get(0);
        } catch (Exception e) {
            return null;
        }
    }

    public JsonNode getCertificateProfileForTMSRA(HttpServletRequest request, String language,
            String certificateAuthorityCode, String certificatePurposeCode, String formFactorCode,
            Boolean renewProfileEnabled, String certificateProfileCode) {
        Map<String, String> jsonObject = new HashMap<>();
        jsonObject.put("language", language);
        jsonObject.put("certificateAuthorityCode", certificateAuthorityCode);
        jsonObject.put("certificatePurposeCode", certificatePurposeCode);
        jsonObject.put("formFactorCode", formFactorCode);
        jsonObject.put("renewProfileEnabled", renewProfileEnabled.toString());
        jsonObject.put("certificateProfileCode", certificateProfileCode);
        try {
            String json = mapper.writeValueAsString(jsonObject);
            RequestBody requestBody = RequestBody.create(JSON, json);
            Response response = sendRequestService.postRequestAuth(requestBody, getCertificateProfileForTMSRAURL,
                    request);
            String responseBody = response.body().string();
            JsonNode root = mapper.readTree(responseBody);
            List<JsonNode> certificateProfileInfo = root.findValues("certificateProfileInfo");
            if (certificateProfileInfo.size() == 0) {
                return mapper.valueToTree("[]");
            }
            return certificateProfileInfo.get(0);
        } catch (Exception e) {
            return null;
        }

    }

    public JsonNode getCertificateComponentForTMSRA(HttpServletRequest request, String language,
            String certificateAuthorityCode, String certificatePurposeCode, String formFactorCode) {
        Map<String, String> jsonObject = new HashMap<>();
        jsonObject.put("language", language);
        jsonObject.put("certificateAuthorityCode", certificateAuthorityCode);
        jsonObject.put("certificatePurposeCode", certificatePurposeCode);
        jsonObject.put("formFactorCode", formFactorCode);
        try {
            String json = mapper.writeValueAsString(jsonObject);
            RequestBody requestBody = RequestBody.create(JSON, json);
            Response response = sendRequestService.postRequestAuth(requestBody, getCertificateComponentForTMSRAURL,
                    request);
            String responseBody = response.body().string();
            JsonNode root = mapper.readTree(responseBody);
            List<JsonNode> certificateComponentInfo = root.findValues("certificateComponentInfo");
            if (certificateComponentInfo.size() == 0) {
                return mapper.valueToTree("[]");
            }
            return certificateComponentInfo.get(0);
        } catch (Exception e) {
            return null;
        }
    }
}
