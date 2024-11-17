package org.xinen.ioc.test;

import org.junit.Test;
import org.xinen.ioc.PropertyValue;
import org.xinen.ioc.PropertyValues;
import org.xinen.ioc.factory.config.BeanDefinition;
import org.xinen.ioc.factory.config.BeanReference;
import org.xinen.ioc.factory.support.DefaultListableBeanFactory;

public class test_BeanFactory {

    @Test
    public void test1(){
        // 1. 初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.注册 UserDao
        beanFactory.registerBeanDefinition("userDao",new BeanDefinition(UserDao.class));

        // 3.使用 UserService 填充属性(uId、userDao)
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        // 4.使用 UserService 注册Bean对象
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 5.使用 UserService 获取Bean对象
        UserService bean = (UserService)beanFactory.getBean("userService");
        bean.queryUserInfo();
    }
}