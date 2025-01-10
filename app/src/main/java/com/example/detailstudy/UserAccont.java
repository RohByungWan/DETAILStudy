package com.example.detailstudy;

/**
 * 사용자 계정 정보 모델 클래스
 */
public class UserAccont {

    private String idToken;     // Firebase Uid 고유 토큰 정보
    private String emalilId;    // 이메일 아이디
    private String password;    // 비밀번호
    private String nameId;      // 사용자 닉네임

    public UserAccont () { } // 파이어베이스에선 모델클래스로 가지고 올때 사용


    public String getNameId(String displayName) {
        return nameId;
    }

    public void setNameId(String nameId) {
        this.nameId = nameId;
    }
    public String getEmalilId(String email) {
        return emalilId;
    }

    public void setEmalilId(String emalilId) {
        this.emalilId = emalilId;
    }

    public String getPassword(String strPwd) {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }
}
