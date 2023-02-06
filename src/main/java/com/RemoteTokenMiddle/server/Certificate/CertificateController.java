package com.RemoteTokenMiddle.server.Certificate;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RemoteTokenMiddle.server.Reponse.ResponseHandler;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("api/v1/certificate")
public class CertificateController {
    private final CertificateService certificateService;

    @Autowired
    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping("/getCertificateAuthorityForTMSRA")
    public ResponseEntity<Object> getCertificateAuthorityForTMSRA(HttpServletRequest request,
            @RequestParam String language) {
        JsonNode certificateAuthorityInfo = certificateService.getCertificateAuthorityForTMSRA(request, language);
        if (certificateAuthorityInfo == null)
            return ResponseHandler.reponseBuilder("Not Found", HttpStatus.NOT_FOUND, null);
        else
            return ResponseHandler.reponseBuilder("Successful", HttpStatus.OK, certificateAuthorityInfo);
    }

    @GetMapping("/getCertificatePurposeForTMSRA")
    public ResponseEntity<Object> getCertificateAuthorityCode(HttpServletRequest request, @RequestParam String language,
            @RequestParam(required = false) String certificateAuthorityCode) {
        JsonNode certificatePurposeInfo = certificateService.getCertificatePurposeForTMSRA(request, language,
                certificateAuthorityCode);
        if (certificatePurposeInfo == null)
            return ResponseHandler.reponseBuilder("Not Found", HttpStatus.NOT_FOUND, null);
        else
            return ResponseHandler.reponseBuilder("Successful", HttpStatus.OK, certificatePurposeInfo);
    }

    @GetMapping("/getCertificateProfileForTMSRA")
    public ResponseEntity<Object> getCertificateProfileForTMSRA(HttpServletRequest request,
            @RequestParam String language,
            @RequestParam(required = false) String certificateAuthorityCode,
            @RequestParam(required = false) String certificatePurposeCode,
            @RequestParam(required = false) String formFactorCode,
            @RequestParam(required = false, defaultValue = "false") Boolean renewProfileEnabled,
            @RequestParam(required = false) String certificateProfileCode) {
        JsonNode certificateProfileInfo = certificateService.getCertificateProfileForTMSRA(request, language,
                certificateAuthorityCode, certificatePurposeCode, formFactorCode, renewProfileEnabled,
                certificateProfileCode);
        if (certificateProfileInfo == null)
            return ResponseHandler.reponseBuilder("Not Found", HttpStatus.NOT_FOUND, null);
        else
            return ResponseHandler.reponseBuilder("Successful", HttpStatus.OK, certificateProfileInfo);
    }

    @GetMapping("/getCertificateComponentForTMSRA")
    public ResponseEntity<Object> getCertificateComponentForTMSRA(HttpServletRequest request,
            @RequestParam String language,
            @RequestParam(required = false) String certificateAuthorityCode,
            @RequestParam(required = false) String certificatePurposeCode,
            @RequestParam(required = false) String formFactorCode) {
        JsonNode certificateComponentInfo = certificateService.getCertificateComponentForTMSRA(request, language,
                certificateAuthorityCode, certificatePurposeCode, formFactorCode);
        if (certificateComponentInfo == null)
            return ResponseHandler.reponseBuilder("Not Found", HttpStatus.NOT_FOUND, null);
        else
            return ResponseHandler.reponseBuilder("Successful", HttpStatus.OK, certificateComponentInfo);
    }
}
