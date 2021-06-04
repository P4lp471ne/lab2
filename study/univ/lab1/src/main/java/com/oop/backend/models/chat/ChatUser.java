package com.oop.backend.models.chat;

import com.oop.backend.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table
@RequiredArgsConstructor
public class ChatUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NonNull
    private Chat chat;

    @OneToOne(fetch = FetchType.LAZY)
    @NonNull
    private User user;

    public ChatUser() {

    }
}
