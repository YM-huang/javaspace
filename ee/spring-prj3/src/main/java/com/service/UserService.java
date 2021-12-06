package com.service;

import com.dao.ICustomerDAO;
import com.po.Customer;

public class UserService extends IUserService {
    private ICustomerDAO customerDAO = null;
    public void setCustomerDAO(ICustomerDAO customerDAO) {
        System.out.println("call setCustomerDAO in UserService.");
        this.customerDAO = customerDAO;
    }
    @Override
    public void addUser(Customer cust) {
        System.out.println("execute --addUser()-- method.");
        customerDAO.save(cust);
    }
    public void delUser(Customer cust) {  }
    public void updateUser(Customer cust) {  }
    public void findUser(Customer cust) {  }
}
