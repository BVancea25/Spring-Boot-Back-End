package com.example.jobproject.controller;


import com.example.jobproject.Models.ChatMessage;
import com.example.jobproject.config.SecurityConfig;
import com.example.jobproject.dto.CallSignalDTO;
import com.example.jobproject.repository.ChatMessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;


@Controller
@CrossOrigin(allowCredentials = "true")
public class WebSocketController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
    @MessageMapping("/private-message")
    private ChatMessage receiveMessage(@Payload ChatMessage chatMessage) {
        try {
            logger.info("Received message: {}", chatMessage.getMessage());
            logger.info("From: {} to: {}", chatMessage.getSenderEmail(), chatMessage.getReceiverEmail());


            String destination = "/user/" + chatMessage.getReceiverEmail() + "/private";
            logger.info("Sending to destination: {}", destination);

            chatMessageRepository.save(chatMessage);
            simpMessagingTemplate.convertAndSend(destination, chatMessage);

            return chatMessage;
        } catch (Exception e) {
            logger.error("Error processing message", e);
            throw e;
        }
    }

}
