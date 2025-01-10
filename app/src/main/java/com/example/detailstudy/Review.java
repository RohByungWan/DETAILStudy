package com.example.detailstudy;

public class Review {
    private String mEtemail;
    private String nickname;
    private double rating;
    private String content;

    public Review () { }
    public Review(String email, String content) {
        this.mEtemail = email;
        this.nickname = nickname;
        this.content = content;
    }
    public String getEmail() {
        return mEtemail;
    }

    public void setEmail(String email) {
        this.mEtemail = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

// 구체적인 구현 클래스
class UserReview extends Review {
    public UserReview(String email, double rating, String content) {
        super(email,  content);
    }
}