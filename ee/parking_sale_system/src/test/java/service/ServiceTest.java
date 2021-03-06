package service;

import com.bean.User;
import com.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @RunWith就是一个运行器
 * SpringJUnit4ClassRunner.class 就是指用SpringJUnit4ClassRunner来运行，让测试运行于spring测试环境
 *@ContextConfiguration Spring整合JUnit4测试时，使用注解引入多个配置文件
 *
 * @author sushuai
 * @date 2019/03/03/22:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class ServiceTest {

    @Autowired
    private UserService userService;


    /**
     * 1、登录时通过用户名来确认用户名是否存在
     */
    @Test
    public void selectUserByUserName() {
        System.out.println("service测试开始。。。。");

        System.out.println(userService.selectUserByUserName("张三"));

        System.out.println("service测试结束。。。。");
    }


    /**
     * 2、注册时往user表里面插入用户名和密码
     */
    @Test
    public void insertUser(){
        System.out.println("service测试开始。。。。");

        User user = new User();
        user.setUsername("王浩");
        user.setPassword("123");
        System.out.println(userService.insertUser(user));

        System.out.println("service测试结束。。。。");
    }
}
