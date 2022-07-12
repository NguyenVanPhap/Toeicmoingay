package com.elearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elearning.entities.CommentPost;
import com.elearning.entities.Post;
import com.elearning.helper.ApiRes;
import com.elearning.repository.CommentPostRepository;

@Service
public class CommentPostService {
    @Autowired
    CommentPostRepository commentPostRepository;

    public ApiRes<Object> findByPost(Post post) {
        ApiRes<Object> apiRes = new ApiRes<Object>();

        try {
            List<CommentPost> lstCommentPost = commentPostRepository.findByPost(post);
            apiRes.setObject(lstCommentPost);
        } catch (Exception ex) {
            apiRes.setError(true);
            apiRes.setErrorReason(ex.getMessage());
        }
        return apiRes;
    }

    public ApiRes save(CommentPost commentPost) {
        ApiRes<Object> apiRes = new ApiRes<Object>();

        try {
            commentPostRepository.save(commentPost);
            apiRes.setObject(true);
        } catch (Exception ex) {
            apiRes.setError(true);
            apiRes.setErrorReason(ex.getMessage());
        }
        return apiRes;
    }
}
