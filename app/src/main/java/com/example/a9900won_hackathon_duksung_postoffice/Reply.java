package com.example.a9900won_hackathon_duksung_postoffice;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reply {
    String postWithReplyTopic; // 답글 달린 원 게시글 Topic
    String replyWriterName; // 작성자 명
    String replyTime; // 작성 날짜
    String replyContent; // 게시글 내용
    String replyTopic; // 게시글 토픽

    Boolean replyIsAnonymity = false; // 익명


    public Reply (String postWithReplyTopic, String replyWriterName, String replyContent, String replyTopic, Boolean replyIsAnonymity) {
        this.postWithReplyTopic = postWithReplyTopic;
        this.replyWriterName = replyWriterName;
        this.replyContent = replyContent;
        this.replyTopic = replyTopic;
        this.replyIsAnonymity = replyIsAnonymity;
        this.replyTime = getTime();
    }


    public Boolean getReplyIsAnonymity() {
        return replyIsAnonymity;
    }

    public String getPostWithReplyTopic() {
        return postWithReplyTopic;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public String getReplyTime() {
        return replyTime;
    }

    public String getReplyTopic() {
        return replyTopic;
    }

    public String getReplyWriterName() {
        return replyWriterName;
    }

    public void setPostWithReplyTopic(String postWithReplyTopic) {
        this.postWithReplyTopic = postWithReplyTopic;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public void setReplyIsAnonymity(Boolean replyIsAnonymity) {
        this.replyIsAnonymity = replyIsAnonymity;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }

    public void setReplyTopic(String replyTopic) {
        this.replyTopic = replyTopic;
    }

    public void setReplyWriterName(String replyWriterName) {
        this.replyWriterName = replyWriterName;
    }

    // 현재 시간을 "yyyy-MM-dd hh:mm:ss"로 표시하는 메소드
    public String getTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String getTime = dateFormat.format(date);

        return getTime;
    }
}
