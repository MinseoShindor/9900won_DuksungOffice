package com.example.a9900won_hackathon_duksung_postoffice;

public class User {
    String id; // 아이디로 사용되는 학번
    String name;
    String major;
    String uid;
    String myPosts; // 내가 쓴 글
    String myLikes; // 내가 공감한 글
    String myScraps; // 내가 스크랩한 글
    String myReplies; // 내가 쓴 답글

    public User (String id, String name, String major) {
        this.id = id;
        this.name = name;
        this.major = major;
    }

    public String getId() {
        return id;
    }

    public String getMajor() {
        return major;
    }

    public String getName() {
        return name;
    }

    public String getUid() {
        return uid;
    }

    public String getMyLikes() {
        return myLikes;
    }

    public String getMyPosts() {
        return myPosts;
    }

    public String getMyReplies() {
        return myReplies;
    }

    public String getMyScraps() {
        return myScraps;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setMyLikes(String myLikes) {
        this.myLikes = myLikes;
    }

    public void setMyPost(String myPosts) {
        this.myPosts = myPosts;
    }

    public void setMyReply(String myReplies) {
        this.myReplies = myReplies;
    }

    public void setMyScrap(String myScraps) {
        this.myScraps = myScraps;
    }
}
