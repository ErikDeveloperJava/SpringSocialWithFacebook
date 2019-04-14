package example.spring.social.with.facebook.controller;

import example.spring.social.with.facebook.config.CurrentUser;
import example.spring.social.with.facebook.model.Post;
import example.spring.social.with.facebook.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/post")
    public String createPostGet(){
        return "create_post";
    }

    @PostMapping("/post")
    public String createPostPost(Post post, @AuthenticationPrincipal CurrentUser currentUser){
        post.setCreateDate(new Date());
        post.setUser(currentUser.getUser());
        postService.add(post);
        return "redirect:/";
    }
}