package com.example.jobproject.config;


import com.example.jobproject.util.SignalingWebSocketHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.session.web.socket.config.annotation.AbstractSessionWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
@EnableWebSocket
public class WebSocketConfiguration extends AbstractSessionWebSocketMessageBrokerConfigurer implements WebSocketConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/user");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
    }

    @Override
    protected void configureStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
      stompEndpointRegistry.addEndpoint("/ws").setAllowedOrigins("http://localhost:3000").withSockJS();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        registry.addHandler(new SignalingWebSocketHandler(), "/socket").setAllowedOrigins("http://localhost:3000");
    }




}

