package com.example.WishAndFish.repository;
import com.example.WishAndFish.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
