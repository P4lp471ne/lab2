package com.oop.backend.controllers;

import com.oop.backend.config.UserDetailsServiceImpl;
import com.oop.backend.controllers.utils.Utils;
import com.oop.backend.models.user.Contact;
import com.oop.backend.models.user.User;
import com.oop.backend.repositories.ContactRepository;
import com.oop.backend.repositories.PersonalChatRepository;
import com.oop.backend.repositories.UserRepository;
import com.oop.backend.responses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    PersonalChatRepository personalChatRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private User getMe(){
        String username = Utils.getUsername();
        return userRepository.findByUsername(username);
    }

    @GetMapping("/contacts")
    public UsersListResponse getContacts() {
        return new UsersListResponse(contactRepository.findContactsByOwner(getMe()).stream().map(Contact::getContact)
                .collect(Collectors.toList()));
    }

    @GetMapping("/news")
    public NewsResponse getNews() {
        return new NewsResponse(contactRepository.findContactsByOwner(getMe()).stream().map(Contact::getContact)
                .collect(Collectors.toList()));
    }

    @GetMapping("/chats")
    public GetChatsResponse getChats() {
        User user =getMe();
        return new GetChatsResponse(personalChatRepository.findByFirstOrSecond(user, user));
    }

    @GetMapping("/user")
    public List<UserPublicData> findUser(@RequestParam String username) {
        return (new UsersListResponse(userRepository.findByUsernameStartsWith(username))).getUsersData();
    }

    @PostMapping("/addContact")
    public AddContactResponse addContact(@RequestParam Long id) {
        Optional<User> searchRes = userRepository.findById(id);
        if (searchRes.isPresent()){
            User user = searchRes.get();
            User client = getMe();
            Contact contact = new Contact(client, user);
            if (!contactRepository.findContactsByOwner(client).contains(contact)) contactRepository.save(contact);

            return new AddContactResponse("added", new UserPublicData(user));
        }
        return new AddContactResponse("user not found", null);
    }
}
