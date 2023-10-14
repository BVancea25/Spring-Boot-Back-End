package com.example.jobproject.auth;

import com.example.jobproject.Models.Role;
import com.example.jobproject.Models.User;
import com.example.jobproject.config.JwtService;
import com.example.jobproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;


    private final AuthenticationManager authenticationManager;


    private final JwtService jwtService;
    public AuthenticationResponse register(RegisterRequest request) {
        System.out.println(request);
         var user= User.builder()
                 .firstName(request.getFirstName())
                 .lastName(request.getLastName())
                 .email(request.getEmail())
                 .phone(request.getPhone())
                 .role(seeRole(request.getRole()))
                 .password(passwordEncoder.encode(request.getPassword()))
                 .build();
         userRepository.save(user);
         var jwtToken=jwtService.generateJWT(user);
         return AuthenticationResponse.builder().jwt(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );
        //corecte
        var user=userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken=jwtService.generateJWT(user);
        return AuthenticationResponse.builder().jwt(jwtToken).build();
    }

    private Role seeRole(String role){
        if(Objects.equals(role, "USER")){
            return Role.USER;
        }
        else {
            return Role.EMPLOYER;
        }
    }
}
