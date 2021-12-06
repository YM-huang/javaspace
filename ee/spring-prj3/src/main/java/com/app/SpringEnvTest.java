package com.app;

import com.po.Customer;
import com.service.IUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringEnvTest {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        IUserService userService = (IUserService)
                ctx.getBean("userService");
        Customer cust = new Customer();
        cust.setAccount("SPRING");
        cust.setPassword("SPRING");
        userService.addUser(cust);
    }
}
