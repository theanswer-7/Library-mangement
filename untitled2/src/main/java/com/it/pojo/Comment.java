package com.it.pojo;

import java.util.Date;

public class Comment {
    private Integer id;
    private Integer blogId;
    private String content;
    private String commentator;
    private Date commentTime;

    public Comment() {
    }

    // 必须有！！！
    public Integer getId() {
        return id;
    }

    // 必须有！！！
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommentator() {
        return commentator;
    }

    public void setCommentator(String commentator) {
        this.commentator = commentator;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", commentator='" + commentator + '\'' +
                ", commentTime=" + commentTime +
                '}';
    }
}