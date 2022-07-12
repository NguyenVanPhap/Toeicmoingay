package com.elearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elearning.entities.CommentCourse;
import com.elearning.entities.Course;
import com.elearning.helper.ApiRes;
import com.elearning.repository.CommentCourseRepository;

@Service
public class CommentCourseService {
    @Autowired
    CommentCourseRepository commentCourseRepository;

    public ApiRes<Object> findByCourse(Course course) {
        ApiRes<Object> apiRes = new ApiRes<Object>();

        try {
            List<CommentCourse> lstCommentCourses = commentCourseRepository.findByCourse(course);
            apiRes.setObject(lstCommentCourses);
        } catch (Exception ex) {
            apiRes.setError(true);
            apiRes.setErrorReason(ex.getMessage());
        }
        return apiRes;
    }

    public ApiRes save(CommentCourse commentCourse) {
        ApiRes<Object> apiRes = new ApiRes<Object>();

        try {
            commentCourseRepository.save(commentCourse);
            apiRes.setObject(true);
        } catch (Exception ex) {
            apiRes.setError(true);
            apiRes.setErrorReason(ex.getMessage());
        }
        return apiRes;
    }
}
