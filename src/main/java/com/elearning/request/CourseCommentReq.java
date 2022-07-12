package com.elearning.request;

import java.util.Date;

public class CourseCommentReq {
    private Integer courseId;

    private String contentCmtCourse;

    private Date time;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getContentCmtCourse() {
        return contentCmtCourse;
    }

    public void setContentCmtCourse(String contentCmtCourse) {
        this.contentCmtCourse = contentCmtCourse;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
