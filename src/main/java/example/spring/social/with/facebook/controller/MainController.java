package example.spring.social.with.facebook.controller;

import example.spring.social.with.facebook.config.CurrentUser;
import example.spring.social.with.facebook.model.User;
import example.spring.social.with.facebook.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private PostService postService;

    @GetMapping("")
    public String main(@AuthenticationPrincipal CurrentUser currentUser, Model model){
        User user = currentUser.getUser();
        model.addAttribute("postList",postService.getByUserId(user.getId()));
        model.addAttribute("user",user);
        return "index";
    }
}