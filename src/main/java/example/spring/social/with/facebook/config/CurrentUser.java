package example.spring.social.with.facebook.config;

import example.spring.social.with.facebook.model.User;
import lombok.Getter;
import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User{

    @Getter
    private User user;

    public CurrentUser(User user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getUserType().name()));
        this.user = user;
    }
}
