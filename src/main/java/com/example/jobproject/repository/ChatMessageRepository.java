package com.example.jobproject.repository;

import com.example.jobproject.Models.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage,Integer> {
}
