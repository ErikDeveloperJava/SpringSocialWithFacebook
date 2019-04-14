package example.spring.social.with.facebook.service;

import example.spring.social.with.facebook.model.User;
import example.spring.social.with.facebook.model.UserType;
import example.spring.social.with.facebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void add(User user){
        userRepository.save(user);
    }
}
