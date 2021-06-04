package com.oop.backend.controllers;

import com.oop.backend.controllers.utils.Utils;
import com.oop.backend.models.user.Page;
import com.oop.backend.models.user.Post;
import com.oop.backend.models.user.PostData;
import com.oop.backend.models.user.User;
import com.oop.backend.repositories.PageRepository;
import com.oop.backend.repositories.PostRepository;
import com.oop.backend.repositories.UserRepository;
import com.oop.backend.responses.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ArticleController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PageRepository pageRepository;

    private User getMe(){
        String username = Utils.getUsername();
        return userRepository.findByUsername(username);
    }

    @GetMapping("/user/{user}/articles")
    public List<Post> getArticles(@PathVariable Long user,
                                  @RequestParam(required = false, defaultValue = "10") int amount) {
        try {
            User u = userRepository.findById(user).orElseThrow();
            return u.getPage().getPosts();
        } catch (Exception e) {
            return null;
        }

    }

    @PostMapping("/new_article")
    public boolean postArticle(@RequestBody Post post) {
        User creator = userRepository.findByUsername(Utils.getUsername());
//        Post post = new Post();
//        post.setPostData(article);
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        long timestamp = System.currentTimeMillis();
        post.setCreated(timestamp);
        postRepository.save(post);
        Page page = creator.getPage();
        page.getPosts().add(post);
        pageRepository.save(page);
//        return postRepository.existsByCreated(timestamp);
        return true;
    }

    @DeleteMapping("/user/{user}/articles/{article_id}")
    public MessageResponse deleteMessage(@PathVariable Long user, @PathVariable Long article_id){
        var article = postRepository.findById(article_id);
        if (userRepository.existsById(user) &&
                getMe().getUsername().equals(userRepository.findById(user).get().getUsername())) {
            if (article.isPresent()) {
                var post = article.get();
                var page = userRepository.getOne(user).getPage();
                if (page.getPosts().stream().map(Post::getId).collect(Collectors.toList()).contains(post.getId())) {
                    page.getPosts().remove(post);
                    postRepository.delete(post);
                    pageRepository.save(page);
                    return new MessageResponse("deleted");
                }
            }
        }
        return new MessageResponse("fail");
    }
}
