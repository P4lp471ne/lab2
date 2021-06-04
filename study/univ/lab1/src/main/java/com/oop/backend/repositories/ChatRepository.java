package com.oop.backend.repositories;

import com.oop.backend.models.chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    boolean existsByTitle(String title);

    Chat getChatByTitle(String title);
}

