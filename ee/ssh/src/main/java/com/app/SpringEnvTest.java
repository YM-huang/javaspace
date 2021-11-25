package com.app;

import com.pojo.Customer;
import com.service.IUserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;

public class SpringEnvTest {
    public static void main(String[] args) throws IOException {

        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        SqlSession sqlSession = (SqlSession) ctx.getBean("sqlSession");
        Customer cust = new Customer();
        cust.setCustomerId(20);
        cust.setAccount("SPRING");
        cust.setPassword("SPRING");
        sqlSession.insert("addUser",cust);




    }
}
