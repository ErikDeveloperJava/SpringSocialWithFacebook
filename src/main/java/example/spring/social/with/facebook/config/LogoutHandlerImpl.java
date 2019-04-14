package example.spring.social.with.facebook.config;

import example.spring.social.with.facebook.model.UserType;
import example.spring.social.with.facebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LogoutHandlerImpl implements LogoutHandler {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                       Authentication authentication) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)authentication;
        CurrentUser currentUser = (CurrentUser) token.getPrincipal();
        if(currentUser.getUser().getUserType() == UserType.FACEBOOK_USER){
            userRepository.deleteById(currentUser.getUser().getId());
        }
        httpServletRequest.getSession().invalidate();
    }
}
