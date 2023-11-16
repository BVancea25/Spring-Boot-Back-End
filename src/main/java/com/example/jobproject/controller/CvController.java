package com.example.jobproject.controller;

import com.example.jobproject.Models.User;
import com.example.jobproject.config.SecurityConfig;
import com.example.jobproject.repository.UserRepository;
import com.example.jobproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassRelativeResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CvController {
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private UserRepository repository;

    @GetMapping("/cv/{email}")
    public ResponseEntity<Resource> downloadCV(@PathVariable String email) {
        try {
            User user=repository.findByEmail(email).orElse(null);

            assert user != null;
            String path = user.getCvPath();
            Resource resource = resourceLoader.getResource("file:" + path);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment: filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
