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

    @Autowired
    public CertificateService(SendRequestService sendRequestService) {
        this.sendRequestService = sendRequestService;
    }

    public JsonNode getCertificateAuthorityForTMSRA(HttpServletRequest request, String language) {
        Map<String, String> jsonObject = new HashMap<>();
        jsonObject.put("language", language);
        RequestBody requestBody = RequestBody.create(JSON, jsonObject.toString());
        try (Response response = sendRequestService.postRequestAuth(requestBody, getCertificateAuthorityForTMSRAURL,
                request)) {
            String responseBody = response.body().string();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseBody);
            JsonNode certificateAuthorityInfo = root.findValues("certificateAuthorityInfo").get(0);
            if (certificateAuthorityInfo.size() == 0)
                return null;
            return certificateAuthorityInfo;
        } catch (Exception e) {
            return null;
        }
    }

    public JsonNode getCertificatePurposeForTMSRA(HttpServletRequest request, String language,
            String certificateAuthorityCode) {
        Map<String, String> jsonObject = new HashMap<>();
        jsonObject.put("language", language);
        jsonObject.put("certificateAuthorityCode", certificateAuthorityCode);
        RequestBody requestBody = RequestBody.create(JSON, jsonObject.toString());
        try (Response response = sendRequestService.postRequestAuth(requestBody, getCertificatePurposeForTMSRAURL,
                request)) {
            String responseBody = response.body().string();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseBody);
            JsonNode certificatePurposeInfo = root.findValues("certificatePurposeInfo").get(0);
            if (certificatePurposeInfo.size() == 0)
                return null;
            return certificatePurposeInfo;
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

        RequestBody requestBody = RequestBody.create(JSON, jsonObject.toString());
        try (Response response = sendRequestService.postRequestAuth(requestBody, getCertificateProfileForTMSRAURL,
                request)) {
            String responseBody = response.body().string();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseBody);
            JsonNode certificateProfileInfo = root.findValues("certificateProfileInfo").get(0);
            if (certificateProfileInfo.size() == 0)
                return null;
            return certificateProfileInfo;
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

        RequestBody requestBody = RequestBody.create(JSON, jsonObject.toString());
        try (Response response = sendRequestService.postRequestAuth(requestBody, getCertificateComponentForTMSRAURL,
                request)) {
            String responseBody = response.body().string();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseBody);
            JsonNode certificateComponentInfo = root.findValues("certificateComponentInfo").get(0);
            if (certificateComponentInfo.size() == 0)
                return null;
            return certificateComponentInfo;
        } catch (Exception e) {
            return null;
        }
    }
}
