package com.oop.backend.models.chat;

import com.oop.backend.models.user.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table
@RequiredArgsConstructor
public class PersonalChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @NotNull
    private User first;

    @ManyToOne
    @NotNull
    private User second;
}