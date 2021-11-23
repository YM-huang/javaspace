package com.app;

import com.service.IUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringEnvTest {
    public static void main(String[] args) {
        //创建 Spring 容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取 UserService 实例
        IUserService userService =
                (IUserService) ctx.getBean("userService");
        userService.register();
    }
}
