package com.yt;


import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContext {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Object userService = context.getBean("UserServiceImpl");
        System.out.println(userService);


    }
}
