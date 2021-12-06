package com.dao;

import com.po.Customer;

public  interface  ICustomerDAO {
    public default void save(Customer cust) {
    }
    public default void delete(Customer transientInstance) {
    }
    public default void update(Customer transientInstance) {
    }
    public default void findByHQL(String hql) {
    }
}
