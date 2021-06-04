package com.oop.backend.models.user;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "post_data")
public class PostData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String text;
}
