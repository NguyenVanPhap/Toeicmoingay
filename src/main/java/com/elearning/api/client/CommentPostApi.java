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

import com.elearning.entities.CommentPost;
import com.elearning.entities.NguoiDung;
import com.elearning.entities.Post;
import com.elearning.repository.NguoiDungRepository;
import com.elearning.request.PostCommentReq;
import com.elearning.service.CommentPostService;
import com.elearning.service.PostService;
import com.elearning.service.NguoiDungService;

@RestController
@RequestMapping("/api/comment/post")
@SessionAttributes("loggedInUser")
public class CommentPostApi {

    @Autowired
    CommentPostService commentPostService;

    @Autowired
    PostService postService;

    @Autowired
    NguoiDungRepository nguoiDungRepository;

    @Autowired
    NguoiDungService nguoiDungService;

    @GetMapping("/id={postId}")
    public ResponseEntity<Object> getListCommentPostId(@PathVariable Integer postId) {
        Post objpost = postService.getPostId(postId);
        return ResponseEntity.ok(commentPostService.findByPost(objpost));
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
    public ResponseEntity<Object> addComment(@RequestBody PostCommentReq newComment, HttpServletRequest request) {
        NguoiDung nguoiDung = getSessionUser((request));

        Post post = new Post();
        post.setPostId((newComment.getPostId()));
        CommentPost comment = new CommentPost();
        Date time = new Date();

        comment.setUserName(nguoiDung.getHoTen());
        comment.setCommentDatePost(time);
        comment.setContentCmtPost(newComment.getContentCmtPost());
        comment.setPost(post);
        return ResponseEntity.ok(commentPostService.save(comment));
    }
}
