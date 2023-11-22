package com.example.jobproject.controller;

import com.example.jobproject.Models.ChatMessage;
import com.example.jobproject.dao.ChatMessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChatMessageController {
    @Autowired
    private ChatMessageDao chatMessageDao;
    @GetMapping("/chat/{senderEmail}/{receiverEmail}")
    public List<ChatMessage> getMessages(@PathVariable("senderEmail") String senderEmail,@PathVariable("receiverEmail") String receiverEmail){

        return  chatMessageDao.getMessagesOfConversation(receiverEmail,senderEmail);
    }
}
