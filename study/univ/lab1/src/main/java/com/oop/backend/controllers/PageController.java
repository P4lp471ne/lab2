package com.oop.backend.controllers;

import com.oop.backend.models.user.Page;
import com.oop.backend.repositories.UserRepository;
import com.oop.backend.responses.CurrentUserDataResponse;
import com.oop.backend.responses.UserPageDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PageController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public CurrentUserDataResponse home() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return new CurrentUserDataResponse(userRepository.findByUsername(username));
        }
        return null;
    }

    @GetMapping("/user/{id}")
    public UserPageDataResponse userPage(@PathVariable String id) {
        return new UserPageDataResponse(userRepository.findById(Long.parseLong(id)).get());
    }
}
