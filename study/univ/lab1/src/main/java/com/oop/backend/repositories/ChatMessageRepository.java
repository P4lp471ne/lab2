package com.oop.backend.repositories;

import com.oop.backend.models.chat.Chat;
import com.oop.backend.models.chat.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<Chat, Message> {
}
