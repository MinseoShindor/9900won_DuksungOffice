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
}
