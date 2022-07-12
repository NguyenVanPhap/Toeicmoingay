package com.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elearning.entities.CommentPost;
import com.elearning.entities.Post;

@Repository
public interface CommentPostRepository extends JpaRepository<CommentPost, Integer> {
    List<CommentPost> findByPost(Post post);

}
