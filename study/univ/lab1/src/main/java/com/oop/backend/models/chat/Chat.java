package com.oop.backend.models.chat;

import com.oop.backend.models.user.User;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table
@RequiredArgsConstructor
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    private String title;
    @OneToMany
    private List<Message> messages;
    @OneToMany
    private List<ChatUser> users;

    public void addUser(User user){
        users.add(new ChatUser(this, user));
    }
    public Chat() {

    }
}
