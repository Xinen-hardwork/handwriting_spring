package org.xinen.ioc.factory.support;

import org.xinen.ioc.BeansException;
import org.xinen.ioc.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args)throws BeansException;
}
