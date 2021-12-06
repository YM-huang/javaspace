package com.dao;

import com.po.Customer;

public class CustomerDAO extends ICustomerDAO {
    @Override
    public void save(Customer transientInstance) {
        System.out.println("execute --save()-- method.");
    }
    @Override
    public void delete(Customer transientInstance) {
        System.out.println("execute --delete()-- method.");
    }
    @Override
    public void update(Customer transientInstance) {
        System.out.println("execute --update()-- method.");
    }
    @Override
    public void findByHQL(String hql) {
        System.out.println("execute --findByHQL()-- method.");
    }
}
