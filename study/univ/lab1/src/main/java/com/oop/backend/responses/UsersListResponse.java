package com.oop.backend.responses;

import com.oop.backend.models.user.Contact;
import com.oop.backend.models.user.User;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;


public class UsersListResponse {
    @Getter
    private List<UserPublicData> usersData;

    public UsersListResponse(List<User> users){
        usersData = new LinkedList<>();
        for (User user : users) {
            UserPublicData data = new UserPublicData(user);

            usersData.add(data);
        }
    }
}
