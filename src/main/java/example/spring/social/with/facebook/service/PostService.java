package example.spring.social.with.facebook.service;

import example.spring.social.with.facebook.model.Post;
import example.spring.social.with.facebook.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public void add(Post post){
        postRepository.save(post);
    }

    public List<Post> getByUserId(int userId){
        return postRepository.findAllByUser_IdOrderByIdDesc(userId);
    }
}
