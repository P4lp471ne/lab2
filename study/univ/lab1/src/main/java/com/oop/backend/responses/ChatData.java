package com.oop.backend.responses;

import com.oop.backend.models.chat.Chat;
import com.oop.backend.models.chat.ChatUser;
import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ChatData {
    private Long id;

    @NonNull
    private String title;

    private List<UserPublicData> users;

    public ChatData(Chat chat) {
        title = chat.getTitle();
        id = chat.getId();
        users = chat.getUsers().stream().map(user -> new UserPublicData(user.getUser())).collect(Collectors.toList());
    }
}
