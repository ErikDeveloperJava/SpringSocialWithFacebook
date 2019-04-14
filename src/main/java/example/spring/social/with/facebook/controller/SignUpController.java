package example.spring.social.with.facebook.controller;

import example.spring.social.with.facebook.model.User;
import example.spring.social.with.facebook.model.UserType;
import example.spring.social.with.facebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signUp")
public class SignUpController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String signUpGet(){
        return "sign_up";
    }

    @PostMapping
    public String signUpPost(User user){
        user.setUserType(UserType.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.add(user);
        return "redirect:/login";
    }
}
