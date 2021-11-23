package com.dao;

public class CustomerDAO extends ICustomerDAO {
    public CustomerDAO(){
        System.out.println("create CustomerDao.");
    }
    @Override
    public void save() {
        System.out.println("execute --save()-- method.");
    }
}
