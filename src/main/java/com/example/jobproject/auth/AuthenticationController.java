package com.example.jobproject.auth;

import com.example.jobproject.config.SecurityConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
    private final AuthenticationService service;
    @PostMapping(value="/register",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_PDF_VALUE})
    public ResponseEntity<String> register(@RequestParam String jsonRequest, @RequestPart MultipartFile cv) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        RegisterRequest registerRequest = objectMapper.readValue(jsonRequest, RegisterRequest.class);
        return ResponseEntity.ok(service.register(registerRequest,cv));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }


}
