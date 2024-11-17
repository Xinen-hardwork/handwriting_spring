package org.xinen.ioc.factory.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.xinen.ioc.PropertyValues;

@Data
public class BeanDefinition {
    private Class beanClass;
    private PropertyValues propertyValues;

    public BeanDefinition(Class beanClass){
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }
    public BeanDefinition(Class beanClass, PropertyValues propertyValues){
        this.beanClass = beanClass;
        this.propertyValues = propertyValues!=null ? propertyValues : new PropertyValues();
    }

}
