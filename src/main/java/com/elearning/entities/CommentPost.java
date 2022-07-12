package com.elearning.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javafx.geometry.Pos;

@Entity
@Table(name = "comment_post")
public class CommentPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "contentcmtpost")
    private String contentCmtPost;

    @Column(name = "time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
    private Date commentDatePost;

    private String userName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "postid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Post post;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContentCmtPost() {
        return contentCmtPost;
    }

    public void setContentCmtPost(String contentCmtPost) {
        this.contentCmtPost = contentCmtPost;
    }

    public Date getCommentDatePost() {
        return commentDatePost;
    }

    public void setCommentDatePost(Date commentDatePost) {
        this.commentDatePost = commentDatePost;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "{" + "\"idcomment\":" + "\"" + id + "\"" + "\"cmtpostcontent\":" + "\"" + contentCmtPost + "\""
                + "," + "\"date\":" + "\"" + commentDatePost + "\""
                + "," + "\"nguoidung\":" + "\"" + userName + "\"" + "\"post\":" + "\"" + post + "\"" + "}";
    }

    public CommentPost() {

    }

    public CommentPost(Integer id, String contentCmtPost, Date commentDatePost, String userName, Post post) {
        super();
        this.id = id;
        this.contentCmtPost = contentCmtPost;
        this.commentDatePost = commentDatePost;
        this.userName = userName;
        this.post = post;
    }
}
