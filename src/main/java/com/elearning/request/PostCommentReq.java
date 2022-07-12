package com.elearning.request;

import java.util.Date;

public class PostCommentReq {
    private Integer postId;

    private String contentCmtPost;

    private Date time;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getContentCmtPost() {
        return contentCmtPost;
    }

    public void setContentCmtPost(String contentCmtPost) {
        this.contentCmtPost = contentCmtPost;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
