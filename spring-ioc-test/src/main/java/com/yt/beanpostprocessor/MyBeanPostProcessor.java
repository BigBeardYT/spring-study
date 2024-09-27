package com.yt.beanpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // 在初始化之前将对象增强
        System.out.println("在初始化之前将对象增强 ... Before Initialization");
        System.out.println(bean.getClass().getName());

        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("在初始化之后, 使用JDK动态代理方式进行对象增强 ... After Initialization");
        Object result = Proxy.newProxyInstance(bean.getClass().getClassLoader(),
                bean.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // proxy就是被代理的对象
                        Date date = new Date(System.currentTimeMillis());
                        System.out.println("方法:" + method.getName() + ", 开始时间:" + date);
                        // 执行要增强的方法
                        Object invoke = method.invoke(bean, args);
                        // 输出结束时间
                        Date date2 = new Date(System.currentTimeMillis());
                        System.out.println("方法:" + method.getName() + ", 结束时间:" + date);
                        return invoke;
                    }
                });

        return result;
//        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
