package com.service;

import com.dao.ICustomerDAO;
import com.pojo.Customer;

public class UserService extends IUserService {
    private ICustomerDAO customerDAO = null;
    private Customer cust;

    public UserService(){
        System.out.println("create UserService.");
    }
    public void setCustomerDAO(ICustomerDAO customerDAO) {
        System.out.println("--setCustomerDAO--");
        this.customerDAO = customerDAO;
    }

    @Override
    public void register(Customer cust) {
        this.cust = cust;
        System.out.println("execute --register()-- method.");
        customerDAO.save(cust);
    }
}
