package com.oop.backend.responses;

import com.oop.backend.models.user.Post;
import com.oop.backend.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

@Data
public class NewsResponse {
    private List<PostData> news;
    public NewsResponse(List<User> contacts) {
        news = new ArrayList<>(contacts.size());
        for (User contact : contacts) {
            for (Post post: contact.getPage().getPosts()) {
                news.add(new PostData(post.getPostData(), contact.getUsername(), contact.getId(), post.getCreated()));
            }
        }
        news.sort(Comparator.comparing(post -> post.created));
        Collections.reverse(news);
    }

    @Data
    @AllArgsConstructor
    private static class PostData{
        private String text;
        private String username;
        private Long id;
        private Long created;
    }
}
