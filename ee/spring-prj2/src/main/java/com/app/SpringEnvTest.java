package com.app;

import com.bean.IItem;
import com.bean.IItemOrder;
import com.event.EmailEvent;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import java.util.Date;
import java.util.Locale;

public class SpringEnvTest {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        EmailEvent ele = new EmailEvent("hello",
                "spring_test@zjut.edu.cn", "this is a test");
        ctx.publishEvent(ele);
    }
}
