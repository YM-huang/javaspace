package com.example.ee_9.dao;

import com.example.ee_9.po.Customer;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CustomerDAO implements ICustomerDAO{

    public CustomerDAO(){
        System.out.println("create CustomerDao.");
    }
    @Override
    public void save(Customer customer) {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        SqlSession sqlSession = (SqlSession) ctx.getBean("sqlSession");
        sqlSession.insert("insertCustomer",customer);
    }
}