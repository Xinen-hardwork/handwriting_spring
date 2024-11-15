package org.xinen.improveBean.test;

import org.junit.Test;
import org.xinen.improveBean.factory.config.BeanDefinition;
import org.xinen.improveBean.factory.support.DefaultListableBeanFactory;

public class ApiTest {

    @Test
    public void test1(){
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);

        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        defaultListableBeanFactory.registerBeanDefinition("userService",beanDefinition);

        UserService userService1 = (UserService)defaultListableBeanFactory.getBean("userService");

        userService1.queryUserInfo();


        UserService userService2 = (UserService)defaultListableBeanFactory.getBean("userService");

        userService2.queryUserInfo();

        System.out.println(userService2 == userService1);
    }
}
