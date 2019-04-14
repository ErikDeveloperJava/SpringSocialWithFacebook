package example.spring.social.with.facebook.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class AppLoginController {

    @GetMapping
    public String login(){
        return "login";
    }
}
