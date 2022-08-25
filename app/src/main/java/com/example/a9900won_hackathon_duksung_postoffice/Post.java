package com.example.a9900won_hackathon_duksung_postoffice;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {
    String postWriterName; // 작성자 명
    String postWriterUid; // 작성자 고유 정보
    String postTime; // 작성 날짜
    String postType; // 게시글 종류 (분야 or 학과...)
    String postTitle; // 게시글 제목
    String postContent; // 게시글 내용
    String postPhotoName; // 첨부된 이미지
    String postTopic; // 게시글 토픽

    Boolean postHasPhoto = false;
    Boolean postIsAnonymity = false; // 익명
    Boolean postIsHot;
    Boolean postHasReply;

    int postLikeCount = 0; // 공감 수
    int postScrapCount = 0; // 스크랩 수
    int postReplyCount = 0; // 답글 수


    // 게시글 작성 처리 (이미지 X)
    public Post (String postWriterName, String postWriterUid, String postType, String postTitle, String postContent, Boolean postHasPhoto, String postTopic, Boolean postIsAnonymity) {
        this.postWriterName = postWriterName;
        this.postWriterUid = postWriterUid;
        this.postType = postType;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postHasPhoto = postHasPhoto;
        this.postTopic = postTopic;
        this.postIsAnonymity = postIsAnonymity;
        this.postLikeCount = 0;
        this.postScrapCount = 0;
        this.postReplyCount = 0;
        this.postIsHot = false;
        this.postHasReply = false;
    }

    // 게시글 작성 처리 (이미지 O)
    public Post (String postWriterName, String postWriterUid, String postType, String postTitle, String postContent, Boolean postHasPhoto, String postPhotoName, String postTopic, Boolean postIsAnonymity) {
        this.postWriterName = postWriterName;
        this.postWriterUid = postWriterUid;
        this.postType = postType;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postHasPhoto = postHasPhoto;
        this.postPhotoName = postPhotoName;
        this.postTopic = postTopic;
        this.postIsAnonymity = postIsAnonymity;
        this.postLikeCount = 0;
        this.postScrapCount = 0;
        this.postReplyCount = 0;
        this.postIsHot = false;
        this.postHasReply = false;
    }


    // 현재 시간을 "yyyy-MM-dd hh:mm:ss"로 표시하는 메소드
    public String getTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String getTime = dateFormat.format(date);

        return getTime;
    }

    public Boolean getPostHasReply() {
        return postHasReply;
    }

    public Boolean getPostIsHot() {
        return postIsHot;
    }

    public String getPostTopic() {
        return postTopic;
    }

    public String getPostWriterUid() {
        return postWriterUid;
    }

    public String getPostContent() {
        return postContent;
    }

    public String getPostTime() {
        return postTime;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getPostPhotoName() {
        return postPhotoName;
    }

    public String getPostType() {
        return postType;
    }

    public Boolean getPostHasPhoto() {
        return postHasPhoto;
    }

    public String getPostWriterName() {
        return postWriterName;
    }

    public int getPostLikeCount() {
        return postLikeCount;
    }

    public int getPostReplyCount() {
        return postReplyCount;
    }

    public Boolean getPostIsAnonymity() {
        return postIsAnonymity;
    }

    public int getPostScrapCount() {
        return postScrapCount;
    }

    public void setPostHasReply(Boolean postHasReply) {
        this.postHasReply = postHasReply;
    }

    public void setPostIsHot(Boolean postIsHot) {
        this.postIsHot = postIsHot;
    }

    public void setPostWriterUid(String postWriterUid) {
        this.postWriterUid = postWriterUid;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public void setPostPhotoName(String postPhotoName) {
        this.postPhotoName = postPhotoName;
    }

    public void setPostHasPhoto(Boolean postHasPhoto) {
        this.postHasPhoto = postHasPhoto;
    }

    public void setPostLikeCount(int postLikeCount) {
        this.postLikeCount = postLikeCount;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public void setPostTopic(String postTopic) {
        this.postTopic = postTopic;
    }

    public void setPostWriterName(String postWriterName) {
        this.postWriterName = postWriterName;
    }

    public void setPostIsAnonymity(Boolean postIsAnonymity) {
        this.postIsAnonymity = postIsAnonymity;
    }

    public void setPostReplyCount(int postReplyCount) {
        this.postReplyCount = postReplyCount;
    }

    public void setPostScrapCount(int postScrapCount) {
        this.postScrapCount = postScrapCount;
    }

}
