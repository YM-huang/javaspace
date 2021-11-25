package com.example.ee_9.service;
import com.example.ee_9.dao.ICustomerDAO;
import com.example.ee_9.po.Customer;

public class UserService implements IUserService {
    private ICustomerDAO customerDAO = null;

    public UserService() {
        System.out.println("create UserService.");
    }

    public void setCustomerDAO(ICustomerDAO customerDAO) {
        System.out.println("--setCustomerDAO--");
        this.customerDAO = customerDAO;
    }

    public void register(Customer loginUser) {
        customerDAO.save(loginUser);
    }
}