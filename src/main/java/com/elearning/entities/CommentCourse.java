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

@Entity
@Table(name = "comment_course")
public class CommentCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "contentcmtcourse")
    private String contentCmtCourse;

    @Column(name = "time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
    private Date commentDateCourse;

    private String userName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "courseid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Course course;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContentCmtCourse() {
        return contentCmtCourse;
    }

    public void setContentCmtCourse(String contentCmtCourse) {
        this.contentCmtCourse = contentCmtCourse;
    }

    public Date getCommentDateCourse() {
        return commentDateCourse;
    }

    public void setCommentDateCourse(Date commentDateCourse) {
        this.commentDateCourse = commentDateCourse;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "{" + "\"idcomment\":" + "\"" + id + "\"" + "\"cmtcoursecontent\":" + "\"" + contentCmtCourse + "\""
                + "," + "\"date\":" + "\"" + commentDateCourse + "\""
                + "," + "\"nguoidung\":" + "\"" + userName + "\"" + "\"course\":" + "\"" + course + "\"" + "}";
    }

    public CommentCourse() {

    }

    public CommentCourse(Integer id, String contentCmtCourse, Date commentDateCourse, String userName, Course course) {
        super();
        this.id = id;
        this.contentCmtCourse = contentCmtCourse;
        this.commentDateCourse = commentDateCourse;
        this.userName = userName;
        this.course = course;
    }
}
