package com.oop.backend.responses;

import com.oop.backend.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddContactResponse {
    private String status;
    private UserPublicData user;
}
