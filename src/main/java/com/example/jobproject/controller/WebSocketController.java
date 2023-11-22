package com.example.jobproject.controller;


import com.example.jobproject.Models.ChatMessage;
import com.example.jobproject.config.SecurityConfig;
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
    private ChatMessage receiveMessage(@Payload ChatMessage chatMessage){
        chatMessageRepository.save(chatMessage);

        logger.info(chatMessage.getMessage());
        logger.info(chatMessage.getSenderEmail()+" to "+chatMessage.getReceiverEmail());
        simpMessagingTemplate.convertAndSendToUser(chatMessage.getReceiverEmail(), "/private", chatMessage);

        return chatMessage;
    }
}
