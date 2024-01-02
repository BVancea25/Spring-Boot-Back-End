package com.example.jobproject.util;

import com.example.jobproject.config.SecurityConfig;
import io.jsonwebtoken.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class SignalingWebSocketHandler extends TextWebSocketHandler {
    List<WebSocketSession> sessions=new CopyOnWriteArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException, java.io.IOException {
        logger.info("Ceva misca");
        for (WebSocketSession webSocketSession : sessions) {
            if (webSocketSession.isOpen() && !session.getId().equals(webSocketSession.getId())) {
                webSocketSession.sendMessage(message);
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("Ceva misca");
        sessions.add(session);
    }

}
