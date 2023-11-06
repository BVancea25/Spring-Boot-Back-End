package com.example.jobproject.config;
import jakarta.annotation.Nonnull;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;

import java.io.IOException;

@Component
public class CorsFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request,@Nonnull HttpServletResponse response, @Nonnull FilterChain filterChain) throws IOException, ServletException {
        //logger.info(request.getMethod()+" "+request.getPathInfo());
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "authorization, content-type, xsrf-token,Authorization");
        response.addHeader("Access-Control-Expose-Headers", "xsrf-token");
        //System.out.println(request.getServletPath());
        if ("OPTIONS".equals(request.getMethod()) || "/logout".equals(request.getServletPath())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}