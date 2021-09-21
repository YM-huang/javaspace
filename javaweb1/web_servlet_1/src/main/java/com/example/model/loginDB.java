package com.example.model;

import java.util.Iterator;
import java.util.Vector;

public class loginDB {
    private static Vector customers = new Vector();
    public void  addUser(String email, String password, String custName, String phone) {
        customers.add( new Customer(email, password, custName, phone));
    }
    //下面方法判断是否存在正确的user
    public static Customer getCustomer(String name, String pwd) {
        Iterator it = customers.iterator();
        Customer customer;
        synchronized(customers) {
            //迭代需要同步
            while(it.hasNext()){
                customer = (Customer)it.next();
                if(customer.equals(name,pwd)) {
                    // 如果返回真，就返回当前 customer
                    return customer;
                }
            }
        }
        return null;
    }
}
