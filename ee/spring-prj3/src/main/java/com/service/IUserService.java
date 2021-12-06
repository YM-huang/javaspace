package com.service;

import com.po.Customer;

public interface IUserService {
    public default void register(Customer cust) {

    }

    public default void addUser(Customer cust) {
    }
}
