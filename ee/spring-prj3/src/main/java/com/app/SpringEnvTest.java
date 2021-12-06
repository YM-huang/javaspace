package com.app;

import com.po.Customer;
import com.service.IUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringEnvTest {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        IUserService userService = (IUserService)
                ctx.getBean("logProxy");
        Customer cust = new Customer();
        cust.setAccount("awG");
        cust.setPassword("SPssas514ING");
        userService.addUser(cust);
    }
}
