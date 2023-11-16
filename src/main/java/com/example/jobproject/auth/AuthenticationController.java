package com.example.jobproject.auth;

import com.example.jobproject.config.SecurityConfig;
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
    @PostMapping(value="/register",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<AuthenticationResponse> register(@RequestPart RegisterRequest request, @RequestParam MultipartFile cv, @RequestHeader HttpHeaders headers){
        logger.info(String.valueOf(headers));
        return ResponseEntity.ok(service.register(request,cv));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }


}
