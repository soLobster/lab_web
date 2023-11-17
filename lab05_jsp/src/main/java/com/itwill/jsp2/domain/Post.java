package com.itwill.jsp2.domain;
//MVC에서 Model에 해당하는 클래스

import java.sql.Timestamp;

//DB에서 POSTS 테이블의 컬럼들과 같은 구조.

import java.time.LocalDateTime;

public class Post {
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;

    public Post() {
    }

    public Post(Long id, String title, String content, String author, Timestamp createdTime, Timestamp modifiedTime) {
        // 다른 생성자 호출:
        this(id, title, content, author, createdTime.toLocalDateTime(), modifiedTime.toLocalDateTime());

//        this.id = id;
//        this.title = title;
//        this.content = content;
//        this.author = author;
//        this.createdTime = createdTime.toLocalDateTime();
//        this.modifiedTime = modifiedTime.toLocalDateTime();
    }

    public Post(Long id, String title, String content, String author, LocalDateTime createdTime,
            LocalDateTime modifiedTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    public String toString() {
        return "Post [id=" + id + ", title=" + title + ", content=" + content + ", author=" + author + ", createdTime="
                + createdTime + ", modifiedTime=" + modifiedTime + "]";
    }

}
