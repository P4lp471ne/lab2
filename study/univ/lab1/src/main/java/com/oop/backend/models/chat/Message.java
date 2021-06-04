package com.oop.backend.models.chat;

import com.oop.backend.models.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 100)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private PersonalChat chat;

    private Date timestamp;

    private MessageStatus status;

    private enum MessageStatus {
        DELIVERED, SEEN
    }
}
