package com.dao;

import com.po.Customer;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CustomerDAO implements ICustomerDAO {
    @Override
    public void save(Customer transientInstance) {

        System.out.println("execute --save()-- method.");
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        SqlSession sqlSession = (SqlSession) ctx.getBean("sqlSession");
        sqlSession.insert("addUser",transientInstance);
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
