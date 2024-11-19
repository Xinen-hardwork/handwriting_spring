package org.xinen.di.factory.support;

import org.xinen.di.BeansException;
import org.xinen.di.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args)throws BeansException;
}
