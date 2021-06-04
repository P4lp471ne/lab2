package com.oop.backend.repositories;

import com.oop.backend.models.chat.PersonalChat;
import com.oop.backend.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface PersonalChatRepository extends JpaRepository<PersonalChat, Long> {
    List<PersonalChat> findByFirstOrSecond(@NotNull User first, @NotNull User second);
}

