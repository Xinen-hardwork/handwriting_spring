package org.xinen.proxy.factory;

import org.xinen.proxy.BeansException;

public interface BeanFactory {
    Object getBean(String name) throws BeansException;
    Object getBean(String name,Object ... args) throws BeansException;
}
