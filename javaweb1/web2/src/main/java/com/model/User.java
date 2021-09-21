package com.model;

public class User {
    private String userid;
    private String password;
    private String userName;
    private String userType;

    public User(String userid, String password, String userName, String userType) {
        this.userid = userid;
        this.password = password;
        this.userName = userName;
        this.userType = userType;
    }

    public User() {
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
