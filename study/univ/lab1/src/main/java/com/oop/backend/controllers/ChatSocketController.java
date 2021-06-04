package com.oop.backend.controllers;

import com.oop.backend.controllers.utils.Utils;
import com.oop.backend.models.chat.Chat;
import com.oop.backend.models.chat.Message;
import com.oop.backend.models.chat.PersonalChat;
import com.oop.backend.models.user.User;
import com.oop.backend.repositories.ChatRepository;
import com.oop.backend.repositories.MessageRepository;
import com.oop.backend.repositories.PersonalChatRepository;
import com.oop.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ChatSocketController {

    @Autowired
    MessageRepository repository;

    @Autowired
    PersonalChatRepository chatRepository;

    @Autowired
    private UserRepository userRepository;

    private User getMe(){
        String username = Utils.getUsername();
        return userRepository.findByUsername(username);
    }

    @PostMapping("/chat/{id}/message")
    public Message sendMessage(@PathVariable long id, @RequestBody Message message){
        message.setTimestamp(new Date(System.currentTimeMillis()));
        message.setUser(getMe());
        message.setChat(chatRepository.findById(id).get());
        return repository.save(message);
    }

//    @MessageMapping("/chat/{id}")
//    @SendTo("/chat/{id}")
//    public Message greeting(@DestinationVariable Long id, Message message) {
//        PersonalChat chat = chatRepository.findById(id).get();
//        message.setChat(chat);
//        User user = getMe();
//        if (user == null) return null;
//        message.setUser(user);
//        return message;
//    }

}