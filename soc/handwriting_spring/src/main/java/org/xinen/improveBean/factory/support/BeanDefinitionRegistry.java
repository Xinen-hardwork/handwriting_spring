package org.xinen.improveBean.factory.support;

import org.xinen.improveBean.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
