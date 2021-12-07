package com.app;

import com.bean.IItem;
import com.bean.IItemOrder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringEnvTest {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        IItemOrder itemorder1 = (IItemOrder)
                ctx.getBean("itemorder1");
        System.out.println("书名：" + itemorder1.getItem().getTitle());
        System.out.println("数量：" + itemorder1.getNumItems());
        IItemOrder itemorder2 = (IItemOrder)
                ctx.getBean("itemorder2");
        System.out.println("书名：" + itemorder2.getItem().getTitle());
        System.out.println("数量：" + itemorder2.getNumItems());
    }
}
