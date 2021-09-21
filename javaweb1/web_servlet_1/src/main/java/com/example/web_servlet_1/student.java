package com.example.web_servlet_1;

public class student {
    private String stuid;
    private String name;
    private String major;

    public student(String stuid, String name, String major) {
        this.stuid = stuid;
        this.name = name;
        this.major = major;
    }

    public student() {
    }

    public String getStuid() {
        return stuid;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
