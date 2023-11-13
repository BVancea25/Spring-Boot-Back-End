package com.example.jobproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import java.io.IOException;
import java.util.logging.SocketHandler;
@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        try {
            registry.addHandler((WebSocketHandler) new SocketHandler(),"/socket")
                    .setAllowedOrigins("*");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
