package com.elearning.api.client;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.elearning.entities.CommentCourse;
import com.elearning.entities.Course;
import com.elearning.entities.NguoiDung;
import com.elearning.repository.NguoiDungRepository;
import com.elearning.request.CourseCommentReq;
import com.elearning.service.CommentCourseService;
import com.elearning.service.CourseService;
import com.elearning.service.NguoiDungService;

@RestController
@RequestMapping("/api/comment/course")
@SessionAttributes("loggedInUser")
public class CommentCourseApi {

    @Autowired
    CommentCourseService commentCourseService;

    @Autowired
    CourseService courseService;

    @Autowired
    NguoiDungRepository nguoiDungRepository;

    @Autowired
    NguoiDungService nguoiDungService;

    @GetMapping("/id={courseId}")
    public ResponseEntity<Object> getListCommentCourseId(@PathVariable Integer courseId) {
        Course objcourse = courseService.getCourseId(courseId);
        return ResponseEntity.ok(commentCourseService.findByCourse(objcourse));
    }

    @ModelAttribute("loggedInUser")
    public NguoiDung loggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        NguoiDung nguoiDung = new NguoiDung();
        if (auth.getClass().isAssignableFrom(OAuth2AuthenticationToken.class)) {
            String principal = auth.getPrincipal().toString();
            String[] part = principal.split(",");
            String name = part[2].split("=")[1];
            nguoiDung.setHoTen(name);
            nguoiDung.setLoginOauth2(true);
            return nguoiDung;
        } else {
            return nguoiDungService.findByEmail(auth.getName());
        }
    }

    public NguoiDung getSessionUser(HttpServletRequest request) {
        NguoiDung nguoiDung = (NguoiDung) request.getSession().getAttribute("loggedInUser");
        return nguoiDung;
    }

    @PostMapping("/add-comment")
    public ResponseEntity<Object> addComment(@RequestBody CourseCommentReq newComment, HttpServletRequest request) {
        NguoiDung nguoiDung = getSessionUser((request));

        Course course = new Course();
        course.setCourseId((newComment.getCourseId()));
        CommentCourse comment = new CommentCourse();
        Date time = new Date();

        comment.setUserName(nguoiDung.getHoTen());
        comment.setCommentDateCourse(time);
        comment.setContentCmtCourse(newComment.getContentCmtCourse());
        comment.setCourse(course);

        return ResponseEntity.ok(commentCourseService.save(comment));
    }
}
