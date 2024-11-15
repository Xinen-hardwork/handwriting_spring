package org.xinen;

/*
 * BeanDefinition 用于定义一个 bean对象 以一个Object类型存储对象
 */

public class BeanDefinition {

    private Object bean;
    public BeanDefinition(Object bean){
        this.bean = bean;
    }

    public Object getBean(){
        return this.bean;
    }
}
