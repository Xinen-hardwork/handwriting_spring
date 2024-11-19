package org.xinen.ResourceLoader.factory.support;

import org.xinen.ResourceLoader.BeansException;
import org.xinen.ResourceLoader.factory.config.BeanDefinition;
import org.xinen.ResourceLoader.factory.io.DefaultResourceLoader;
import org.xinen.ResourceLoader.factory.io.Resource;
import org.xinen.ResourceLoader.factory.io.ResourceLoader;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

    private final BeanDefinitionRegistry registry;
    private ResourceLoader resourceLoader;
    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry  getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
