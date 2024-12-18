package org.xinen.ResourceLoader.factory.support;

import org.xinen.ResourceLoader.BeansException;
import org.xinen.ResourceLoader.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args)throws BeansException;
}
