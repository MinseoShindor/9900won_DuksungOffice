package com.example.a9900won_hackathon_duksung_postoffice;

public class User {
    String id; // 아이디로 사용되는 학번
    String name;
    String major;
    String uid;

    public User (String id, String name, String major, String uid) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.uid = uid;
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
}
