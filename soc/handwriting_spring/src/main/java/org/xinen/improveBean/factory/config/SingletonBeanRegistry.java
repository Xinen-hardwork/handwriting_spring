package org.xinen.improveBean.factory.config;

public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);
    void registerSingleton(String beanName, Object singletonObject);
}
