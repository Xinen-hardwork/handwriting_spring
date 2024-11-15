# IOC容器

### 简单的 spring bean容器

##### spring bean容器包含并管理应用对象的配置和生命周期

+ 承载对象的容器，每个bean的创建交互构建和使用

+ 把bean对象交给spring bean容器管理，bean对象会会被拆解，存储到spring bean容器中，便于管理，相当于把bean对象解耦

+ 实现简单的spring bean容器需要完成Bean对象的定义注册和获取三个部分

  ![E:\books\handwriting_spring\doc\img](E:\books\handwriting_spring\doc\img\uml1.png "简单SpringBean容器的UML类图")

  + BeanDefinition  定义Bean对象，以一个Object类型存储对象

    ```Java
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
    ```

  + BeanFactory  Bean对象的工厂，将bean对象存储到Map中以便获取对象

    ```Java
    package org.xinen;
    import java.util.Map;
    import java.util.concurrent.ConcurrentHashMap;
    /*
     * BeanFactory 生成和使用Bean对象的工厂，将Bean对象的定义存储到Map中以便获取
     */
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
    ```

  + test1  Junit测试类

    ```Java
    import org.junit.Test;
    import org.xinen.BeanDefinition;
    import org.xinen.BeanFactory;
    public class teat_BeanFactory {
        @Test
        public void test1(){
            // 1. 初始化BeanFactory
            BeanFactory beanFactory = new BeanFactory();
            // 2.注册bean
            BeanDefinition beanDefinition = new BeanDefinition(new UserService());
            beanFactory.registerBeanDefinition("userService",beanDefinition);
            // 3.获取bean
            UserService userService = (UserService)beanFactory.getBean("userService");
            userService.queryUserInfo();
        }
    }
    ```

   + UserService  测试的对象

     ```Java
     package org.xinen.test;
     
     public class UserService {
         public void queryUserInfo(){
             System.out.println("查询用户信息");
         }
     }
     ```

---

### 实现Bean对象的定义注册获取

​	通过spring容器创建对象，而不是调用一个实例化的bean对象

##### 完善容器设计

+ 注册Bean对象时，注册一个类信息，而不是实例化对象信息

+ 获取bean对象的时候进行实例化，以及判断bean对象是否已经存在

  ![E:\books\handwriting_spring\doc\img](E:\books\handwriting_spring\doc\img\uml2.png "完善后的uml类图")

  + BeanFactory  是通过AbstractBeanFactory抽象类实现的getBean方法来定义

    ```Java
    public interface BeanFactory {
        Object getBean(String name) throws BeansException;
    }
    
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
    ```

  + AbstractBeanFactory  继承了SingletonBeanRegistry接口的DefaultSingletonBeanRegistry类，具备单例对象的注册方法

    ```Java
    public interface SingletonBeanRegistry {
        Object getSingleton(String beanName);
        void registerSingleton(String beanName, Object singletonObject);
    }
    
    public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry{
        private Map<String, Object> singletonObjects = new HashMap<>();
        @Override
        public Object getSingleton(String beanName) {
            return singletonObjects.get(beanName);
        }
        @Override
        public void registerSingleton(String beanName, Object singletonObject) {
            singletonObjects.put(beanName,singletonObject);
        }
    }
    ```

  + AbstractBeanFactory  定义了getBeanDefinition()和createBean()两种抽象方法，分别由DefaultListableBeanFactory类和AbstractAutowireCapableBeanFactory类实现

  + ```Java
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
    ```

  + DefaultListableBeanFactory  继承抽象类AbstractAutowireCapableBeanFactory，可以调用该抽象类的createBean()方法

    ```Java
    public interface BeanDefinitionRegistry {
        void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
    }
    
    public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry{
        private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();
        @Override
        protected BeanDefinition getBeanDefinition(String name) throws BeansException {
            BeanDefinition beanDefinition = beanDefinitionMap.get(name);
            if(beanDefinition == null) {
                throw new BeansException("No bean named'" + name + "' is defined");
            }
            return beanDefinition;
        }
        @Override
        public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
            beanDefinitionMap.put(beanName,beanDefinition);
        }
    }
    ```

---

### 基于Cglib实现含构造函数类实例化策略

​	基于策略模式实现两种用于实例化对象的方法，JDK、Cglib

##### 实例化没有考虑类中是否有带入参信息的构造函数

+ 在上个版本中添加 InstantiationStrategy 实例化策略接口、开发对应的JDK和Cglib实例化方法，补充带参数的getBean()构造函数

  ![E:\books\handwriting_spring\doc\img](E:\books\handwriting_spring\doc\img\uml3.png "添加 InstantiationStrategy 实例化策略接口后的uml类图")

  + 在BeanFactory类中新增 getBean()接口

    ```Java
    public interface BeanFactory {
        Object getBean(String name) throws BeansException;
        Object getBean(String name,Object[] ... args) throws BeansException;
    }
    ```

  + 接口InstantiationStrategy的instantiate构造函数添加必要的入参信息

    ```Java
    import java.lang.reflect.Constructor;
    public interface InstantiationStrategy {
        Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args)throws BeansException;
    }
    ```
    

  + JDK 实例化

    ```Java
    public class SimpleInstantiationStrategy implements InstantiationStrategy {
        @Override
        public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
            Class clazz = beanDefinition.getBeanClass();
            try {
                if (null != ctor) {
                    return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
                } else {
                    return clazz.getDeclaredConstructor().newInstance();
                }
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new BeansException("Failed to instantiate [" + clazz.getName() + "]", e);
            }
        }
    }
    ```

  + Cglib 实例化

    ```Java
    public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy{
        @Override
        public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(beanDefinition.getBeanClass());
            enhancer.setCallback(new NoOp() {
                @Override
                public int hashCode() {
                    return super.hashCode();
                }
            });
            if (null == ctor) {
                return enhancer.create();
            }
            return enhancer.create(ctor.getParameterTypes(), args);
        }
    }
    ```

  + AbstractAutowireCapableBeanFactory  创建策略调用

    ```Java
    public abstract  class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
        private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();
        @Override
        protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
            Object bean = null;
            try {
                bean = createBeanInstance(beanDefinition, beanName, args);
            } catch (Exception e) {
                throw new BeansException("Instantiation of bean failed", e);
            }
    
            registerSingleton(beanName, bean);
            return bean;
        }
        protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
            Constructor constructorToUse = null;
            Class<?> beanClass = beanDefinition.getBeanClass();
            Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
            for (Constructor ctor : declaredConstructors) {
                if (null != args && ctor.getParameterTypes().length == args.length) {
                    constructorToUse = ctor;
                    break;
                }
            }
            return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
        }
        public InstantiationStrategy getInstantiationStrategy() {
            return instantiationStrategy;
        }
        public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
            this.instantiationStrategy = instantiationStrategy;
        }
    }
    ```

