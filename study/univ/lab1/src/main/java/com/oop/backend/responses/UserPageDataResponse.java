package com.oop.backend.responses;

import com.oop.backend.models.user.Post;
import com.oop.backend.models.user.User;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class UserPageDataResponse {
    private Long id;
    private String username;
    private String email;
    private List<Post> posts;

    public UserPageDataResponse(){}

    public UserPageDataResponse(User user) {
        id = user.getId();
        email = user.getEmail();
        username = user.getUsername();
        posts = user.getPage().getPosts();
        Collections.reverse(posts);
    }
}
