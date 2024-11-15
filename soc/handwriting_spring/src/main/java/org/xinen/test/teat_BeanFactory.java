package org.xinen.test;

import org.junit.Test;
import org.xinen.BeanDefinition;
import org.xinen.BeanFactory;

public class teat_BeanFactory {

    @Test
    public void test1(){
        // 1. 初始化BeanFactory
        BeanFactory beanFactory = new BeanFactory();

        // 2.注册bean
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("userService",beanDefinition);

        // 3.获取bean
        UserService userService = (UserService)beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
