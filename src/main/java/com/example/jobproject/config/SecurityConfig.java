package com.example.jobproject.config;



import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.Arrays;


import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final CorsFilter corsFilter;
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
    @Primary
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
        http

                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(req ->
                        req
                                .requestMatchers("/auth/**")
                                .permitAll()
                                .requestMatchers(HttpMethod.GET,"/job/{id}")
                                .permitAll()
                                .requestMatchers(HttpMethod.GET,"/job")
                                .permitAll()
                                .requestMatchers(HttpMethod.DELETE,"/job/{id}").hasAuthority("EMPLOYER")
                                .requestMatchers(HttpMethod.POST,"/application").hasAuthority("USER")
                                .requestMatchers(HttpMethod.GET,"/jobs").hasAuthority("EMPLOYER")
                                .requestMatchers(HttpMethod.GET,"/application/employer").hasAuthority("EMPLOYER")
                                .anyRequest()
                                .authenticated()
                )
                .logout((logout)->
                        logout.logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                                .permitAll()

                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }



}

