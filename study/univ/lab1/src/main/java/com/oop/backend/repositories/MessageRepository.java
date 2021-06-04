package com.oop.backend.repositories;

import com.oop.backend.models.chat.Chat;
import com.oop.backend.models.chat.Message;
import com.oop.backend.models.chat.PersonalChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByChat(PersonalChat chat);
}
