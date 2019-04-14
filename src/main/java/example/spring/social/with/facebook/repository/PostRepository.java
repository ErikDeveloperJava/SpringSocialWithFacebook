package example.spring.social.with.facebook.repository;

import example.spring.social.with.facebook.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {

    List<Post> findAllByUser_IdOrderByIdDesc(int userId);
}