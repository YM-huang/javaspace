package com.advice;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import java.lang.reflect.Method;
import java.util.Date;

public class LoggingAdvice implements MethodBeforeAdvice , AfterReturningAdvice {
    @Override
    public void before(Method m, Object[] args, Object target)
            throws Throwable {
        String date1 = (new Date()).toLocaleString();
        System.out.println("信息：[logging before ]["+date1+"]用户 "
                +args[0]+" 尝试修改个人信息...");
    }
    @Override
    public void afterReturning(Object returnValue, Method method,
                               Object[] args, Object target) throws Throwable {
        String date2 = (new Date()).toLocaleString();
        System.out.println("信息：[logging after ]["+date2+"]用户 "
                +args[0]+" 成功修改个人信息...");
    }

}
