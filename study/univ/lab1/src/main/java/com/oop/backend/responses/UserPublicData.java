package com.oop.backend.responses;

import com.oop.backend.models.user.Role;
import com.oop.backend.models.user.User;
import lombok.Data;

import java.util.Set;

@Data
public class UserPublicData{
    private String username;
    private Long id;
    private boolean expired;
    private boolean locked;
    private Set<Role> roles;

    public UserPublicData(User user){
        setExpired(user.isExpired());
        setLocked(user.isLocked());
        setId(user.getId());
        setUsername(user.getUsername());
    }
}