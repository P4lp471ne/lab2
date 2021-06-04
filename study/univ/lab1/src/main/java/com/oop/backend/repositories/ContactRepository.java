package com.oop.backend.repositories;

import com.oop.backend.models.chat.Chat;
import com.oop.backend.models.user.Contact;
import com.oop.backend.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Set<Contact> findContactsByOwner(User owner);
}
