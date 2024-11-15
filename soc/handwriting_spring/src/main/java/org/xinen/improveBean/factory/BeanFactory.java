package org.xinen.improveBean.factory;

import org.xinen.improveBean.BeansException;

public interface BeanFactory {
    Object getBean(String name) throws BeansException;
}
