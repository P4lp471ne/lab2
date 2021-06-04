package com.oop.backend.repositories;

import com.oop.backend.models.user.Post;
import com.oop.backend.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    boolean existsByCreated(Long created);
}
