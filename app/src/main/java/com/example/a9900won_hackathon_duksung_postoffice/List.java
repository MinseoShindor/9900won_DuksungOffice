package com.example.a9900won_hackathon_duksung_postoffice;

public class List {

    String title; // 제목
    String content; // 내용
    String writer; // 작성자
    String date;

    int likes = 0;
    int scraps = 0;
    int replies = 0;

    Boolean hasPhoto = false;

    public List (String title, String content, String writer, String date, int likes, int scraps, int replies, Boolean hasPhoto) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.date = date;
        this.likes = likes;
        this.scraps = scraps;
        this.replies = replies;
        this.hasPhoto = hasPhoto;
    }

    public String getContent() {
        return content;
    }

    public Boolean getHasPhoto() {
        return hasPhoto;
    }

    public int getLikes() {
        return likes;
    }

    public int getScraps() {
        return scraps;
    }

    public int getReplies() {
        return replies;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHasPhoto(Boolean hasPhoto) {
        this.hasPhoto = hasPhoto;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setReplies(int replies) {
        this.replies = replies;
    }

    public void setScraps(int scraps) {
        this.scraps = scraps;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}
