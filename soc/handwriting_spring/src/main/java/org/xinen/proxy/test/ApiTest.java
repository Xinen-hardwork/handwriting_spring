package org.xinen.proxy.test;

import org.junit.Test;
import org.xinen.proxy.factory.config.BeanDefinition;
import org.xinen.proxy.factory.support.DefaultListableBeanFactory;

public class ApiTest {

    @Test
    public void test1(){
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);

        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        defaultListableBeanFactory.registerBeanDefinition("userService",beanDefinition);

        UserService userService2 = (UserService)defaultListableBeanFactory.getBean("userService","hardly");

        userService2.queryUserInfo();

    }
}
