package com.example.jobproject.config;



import jakarta.servlet.http.HttpServletResponse;
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
                                .requestMatchers("/ws")
                                .permitAll()
                                .requestMatchers("/ws/**")
                                .permitAll()
                                .requestMatchers("/socket")
                                .permitAll()
                                .requestMatchers(HttpMethod.DELETE,"/job/{id}").hasAuthority("EMPLOYER")
                                .requestMatchers(HttpMethod.POST,"/application").hasAuthority("USER")
                                .requestMatchers(HttpMethod.GET,"/jobs").hasAuthority("EMPLOYER")
                                .requestMatchers(HttpMethod.GET,"/application/employer").hasAuthority("EMPLOYER")
                                .requestMatchers(HttpMethod.PUT,"/application/emplyoer/{id}").hasAuthority("EMPLOYER")
                                .anyRequest()
                                .authenticated()
                )
                .logout((logout)->
                        logout.logoutSuccessHandler(
                                (httpServletRequest, httpServletResponse, authentication) -> {
                                    httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                                    System.out.println(httpServletRequest.getHeaderNames());
                                    httpServletResponse.setHeader("Access-Control-Allow-Origin" , "http://localhost:3000");
                                })
                                .permitAll()


                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }



}

