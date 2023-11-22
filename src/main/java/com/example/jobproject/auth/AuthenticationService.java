package com.example.jobproject.auth;

import com.example.jobproject.Models.Role;
import com.example.jobproject.Models.User;
import com.example.jobproject.config.JwtService;
import com.example.jobproject.repository.UserRepository;
import com.example.jobproject.service.UserService;
import lombok.RequiredArgsConstructor;



import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final UserService userService;
    private final AuthenticationManager authenticationManager;


    private final JwtService jwtService;
    public String register(RegisterRequest request, MultipartFile cv) {
        String path="";
        try {
            path=userService.saveCV(cv, request.getEmail());
        }catch (Exception e){

        }
         var user= User.builder()
                 .firstName(request.getFirstName())
                 .lastName(request.getLastName())
                 .email(request.getEmail())
                 .phone(request.getPhone())
                 .cvPath(path)
                 .role(seeRole(request.getRole()))
                 .password(passwordEncoder.encode(request.getPassword()))
                 .companyName(request.getCompanyName())
                 .build();

         userRepository.save(user);
        //System.out.println(user.getAuthorities());
         return "registered";
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );

        var user=userRepository.findByEmail(request.getEmail()).orElseThrow();
        //System.out.println(user.getAuthorities());
        var jwtToken=jwtService.generateJWT(user);
        String userRole= user.getAuthorities().toString();
        return AuthenticationResponse
                .builder()
                .jwt(jwtToken)
                .role(userRole)
                .build();
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
