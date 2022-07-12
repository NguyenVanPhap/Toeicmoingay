package com.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elearning.entities.CommentCourse;
import com.elearning.entities.Course;

@Repository
public interface CommentCourseRepository extends JpaRepository<CommentCourse, Integer> {
    List<CommentCourse> findByCourse(Course course);

}
