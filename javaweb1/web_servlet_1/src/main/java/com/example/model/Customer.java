package com.example.model;

public class Customer {
    private String email;
    private String password;
    private String custName;
    private String phone;

    public Customer(String email, String password, String custName, String phone) {
        this.email = email;
        this.password = password;
        this.custName = custName;
        this.phone = phone;
    }

    public Customer() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCustName() {
        return custName;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    //判断当前对象用户名和密码是否相等
    public boolean equals(String uname, String upwd) {
        return getEmail().equals(uname) && getPassword().equals(upwd);
    }

    public Customer(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
