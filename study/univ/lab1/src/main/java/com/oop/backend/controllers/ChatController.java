package com.oop.backend.controllers;

import com.oop.backend.controllers.utils.Utils;
import com.oop.backend.models.chat.Chat;
import com.oop.backend.models.chat.Message;
import com.oop.backend.models.chat.PersonalChat;
import com.oop.backend.models.user.Contact;
import com.oop.backend.models.user.Role;
import com.oop.backend.models.user.User;
import com.oop.backend.repositories.*;
import com.oop.backend.responses.ChatData;
import com.oop.backend.responses.MessageResponse;
import com.oop.backend.responses.ShortChatData;
import com.oop.backend.responses.UserPublicData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    ChatRepository chatRepository;

    @Autowired
    PersonalChatRepository personalChatRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    private User getMe(){
        String username = Utils.getUsername();
        return userRepository.findByUsername(username);
    }

    @GetMapping("/personal/{id}/messages")
    public List<Message> getPersonalChatMessages(@PathVariable long id,
                                     @RequestParam(required = false, defaultValue = "10") int amount) {
        try {
            return messageRepository.findAllByChat(personalChatRepository.findById(id).get());
        }catch (Exception e){
            return null;
        }
    }

    @GetMapping("/{id}")
    public ChatData getChat(@PathVariable int id) {
        return new ChatData(chatRepository.findById((long) id).get());
    }

    @GetMapping("/personal/{user_id}")
    public ShortChatData getPersonalChat(@PathVariable long user_id){
        User client = getMe();
        Optional<User> contact = userRepository.findById(user_id);
        if (contact.isEmpty()) return null;
        List<PersonalChat> chats = personalChatRepository.findByFirstOrSecond(client, client);
        for (PersonalChat chat :
                chats) {
            if (chat.getFirst().getUsername().equals(client.getUsername()) ||
                    chat.getSecond().getUsername().equals(client.getUsername())){
                return new ShortChatData(chat.getId(), new UserPublicData(chat.getFirst()),
                        new UserPublicData(chat.getSecond()));
            }
        }
        PersonalChat chat = new PersonalChat();
        chat.setFirst(client);
        chat.setSecond(contact.get());
        chat = personalChatRepository.save(chat);

        return new ShortChatData(chat.getId(), new UserPublicData(chat.getFirst()),
                new UserPublicData(chat.getSecond()));
    }

    @PostMapping("/create")
    public Chat newChat(@RequestBody String chatTitle) {
        if (!chatRepository.existsByTitle(chatTitle)) {
            Chat chat = new Chat(chatTitle);
            chatRepository.save(chat);
            User me = getMe();
            me.getChats().add(chat);
            userRepository.save(me);
            return chatRepository.getChatByTitle(chatTitle);
        }
        return null;
    }

    @PostMapping("/add_users/{chatId}")
    public MessageResponse addUsersToChat(@PathVariable Long chatId, @RequestBody List<String> users) {
        Optional<Chat> chatSearchRes = chatRepository.findById(chatId);
        if (chatSearchRes.isPresent()) {
            Chat chat = chatSearchRes.get();
            User client = getMe();
            if (chat.getUsers().stream().anyMatch(chatUser -> chatUser.getUser().getId().equals(client.getId()))){
                if(client.getContacts().stream().map(Contact::getContact)
                        .map(User::getUsername).collect(Collectors.toList())
                        .containsAll(users)){
                    users.forEach(username -> {
                        User user = userRepository.findByUsername(username);
                        user.getChats().add(chat);
                        chat.addUser(user);
                        userRepository.save(user);
                    });
                    chatRepository.save(chat);
                    return new MessageResponse("done");
                }
                return new MessageResponse("users not found");
            }
            return new MessageResponse("chat not found");
        }
        return new MessageResponse("chat not found");
    }
}
