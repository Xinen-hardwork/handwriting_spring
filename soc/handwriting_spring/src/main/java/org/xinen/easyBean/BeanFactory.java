package org.xinen.easyBean;

/*
 * BeanFactory 生成和使用Bean对象的工厂，将Bean对象的定义存储到Map中以便获取
 */

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory {

    private Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /*
     * 根据Bean的名称获取Bean对象
     */
    public Object getBean(String name){
        return beanDefinitionMap.get(name).getBean();
    }

    /*
     * 注册BeanDefinition 对象
     */
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition){
        beanDefinitionMap.put(name,beanDefinition);
    }
}
