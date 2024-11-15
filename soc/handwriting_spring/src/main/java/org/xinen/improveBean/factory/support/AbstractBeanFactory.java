package org.xinen.improveBean.factory.support;


import org.xinen.improveBean.BeansException;
import org.xinen.improveBean.factory.BeanFactory;
import org.xinen.improveBean.factory.config.BeanDefinition;
import org.xinen.improveBean.factory.config.DefaultSingletonBeanRegistry;
import org.xinen.improveBean.factory.config.BeanDefinition;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String name) throws BeansException {
        Object bean = getSingleton(name);
        if(bean != null){
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }
    protected abstract BeanDefinition getBeanDefinition(String name) throws BeansException;
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;
}
