package org.xinen.improveBean.factory.support;

import org.xinen.improveBean.BeansException;
import org.xinen.improveBean.factory.config.BeanDefinition;

public abstract  class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException{
        Object bean = null;
        try{
            bean = beanDefinition.getBeanClass().newInstance();
        }catch (Exception e){
            throw new BeansException("Instantiation of bean failed", e);
        }
        registerSingleton(beanName, bean);
        return bean;
    }
}
