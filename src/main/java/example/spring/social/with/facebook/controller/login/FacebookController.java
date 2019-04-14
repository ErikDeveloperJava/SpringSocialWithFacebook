package example.spring.social.with.facebook.controller.login;

import example.spring.social.with.facebook.config.CurrentUser;
import example.spring.social.with.facebook.model.User;
import example.spring.social.with.facebook.model.UserType;
import example.spring.social.with.facebook.service.FacebookService;
import example.spring.social.with.facebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login/facebook")
public class FacebookController {

    @Autowired
    private FacebookService facebookService;

    @Autowired
    private UserService userService;

    @GetMapping
    public RedirectView loginFacebook(){
        RedirectView redirectView = new RedirectView();
        String redirectUrl = facebookService.createRedirectUrl();
        redirectView.setUrl(redirectUrl);
        return redirectView;
    }

    @GetMapping("/confirmed")
    public String loginFacebookConfirmed(@RequestParam("code")String code, HttpSession session){
        org.springframework.social.facebook.api.User facebookUser = facebookService.getCurrentUserByCode(code);
        User appUser = User
                .builder()
                .email(facebookUser.getEmail())
                .name(facebookUser.getFirstName())
                .surname(facebookUser.getLastName())
                .password("********************")
                .userType(UserType.FACEBOOK_USER)
                .build();
        //save new user with facebook profile
        userService.add(appUser);

        //here user is added to httpSession
        CurrentUser currentUser = new CurrentUser(appUser);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(currentUser, null, currentUser.getAuthorities());
        SecurityContext securityContext = new SecurityContextImpl(token);
        session.setAttribute("SPRING_SECURITY_CONTEXT",securityContext);

        //user has already login by facebook profile
        return "redirect:/";
    }
}
