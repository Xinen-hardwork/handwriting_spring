package org.xinen.proxy.factory.support;

import org.xinen.proxy.BeansException;
import org.xinen.proxy.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args)throws BeansException;
}
