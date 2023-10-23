package com.example.jobproject.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;


@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader=request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if(authHeader==null ||!authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        jwt=authHeader.substring(7);
        userEmail=jwtService.extractUsername(jwt);
        getAllHeaders(response);
        if(userEmail!=null && SecurityContextHolder.getContext().getAuthentication() == null ){//daca avem email-ul utilizatorului si utilziatorul nu este autentificat
            UserDetails userDetails=this.userDetailsService.loadUserByUsername(userEmail);//detaliile utilziatorului din baza de date

            if(jwtService.isTokenValid(jwt,userDetails)){//daca token-ul este valid

                UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
            filterChain.doFilter(request,response);
        }
    }

    private void getAllHeaders(HttpServletResponse response) {
        // Get an enumeration of all header names
        Collection<String> headerNames = response.getHeaderNames();

        for (String headerName : headerNames) {
            Collection<String> headerValues = response.getHeaders(headerName);

            System.out.println("Header: " + headerName);
            for (String value : headerValues) {
                System.out.println("Value: " + value);
            }
        }
    }

}
