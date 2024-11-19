package org.xinen.ResourceLoader.factory.support;

import org.xinen.ResourceLoader.BeansException;
import org.xinen.ResourceLoader.factory.io.Resource;
import org.xinen.ResourceLoader.factory.io.ResourceLoader;

public interface BeanDefinitionReader {

    BeanDefinitionRegistry  getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource)  throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;
}
