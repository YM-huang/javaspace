package com.dao;

import com.pojo.Customer;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;

public class CustomerDAO extends ICustomerDAO {
    public CustomerDAO(){
        System.out.println("create CustomerDao.");
    }
    @Override
    public void save(Customer cust) {
        System.out.println("execute --save()-- method.");
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        SqlSession sqlSession = (SqlSession) ctx.getBean("sqlSession");
        sqlSession.insert("addUser",cust);
    }
}
