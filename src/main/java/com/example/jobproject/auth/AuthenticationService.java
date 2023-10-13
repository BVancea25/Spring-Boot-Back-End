package com.example.jobproject.auth;

import com.example.jobproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private final UserRepository userRepository;
    public AuthenticationResponse register(RegisterRequest request) {

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {


    }
}
