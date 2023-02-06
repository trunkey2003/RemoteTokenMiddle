package com.RemoteTokenMiddle.server.Certificate;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RemoteTokenMiddle.Reponse.ResponseHandler;
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
    public ResponseEntity<Object> getCertificateAuthorityForTMSRA(HttpServletRequest request) {
        List<JsonNode> certificateAuthorityInfo = certificateService.getCertificateAuthorityForTMSRA(request);
        if (certificateAuthorityInfo == null)
            return ResponseHandler.reponseBuilder("Not Found", HttpStatus.NOT_FOUND, null);
        else 
            return ResponseHandler.reponseBuilder("Successful", HttpStatus.OK, certificateAuthorityInfo);
    }
}
