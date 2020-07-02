package com.xhs.servicefeign.myfeign;




import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.stream.Stream;

import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;

/**
 * @author xuhan  build  2019/1/7
 */
public class RestClientsRegistrar implements ImportBeanDefinitionRegistrar ,BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        Map<String, Object> attributes =  metadata.getAnnotationAttributes(EnableRestClient.class.getName());

        ClassLoader classLoader = metadata.getClass().getClassLoader();

        //得到注解中所写的类
        Class<?>[] classes = (Class<?>[]) attributes.get("clients");
        //注解中的类必须为接口
        Stream.of(classes).filter(Class::isInterface)
        //仅选择带RestClient注解的接口
                .filter(interfaceClass ->findAnnotation(interfaceClass,RestClient.class)!=null)
                .forEach(restClientClass ->{
                    RestClient restClient = findAnnotation(restClientClass,RestClient.class);
                    String serverName = restClient.name();

                    Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{restClientClass},
                            new RequestMappingMethodInvocationHandler(serverName,beanFactory));

                    String beanName = "restClient" +serverName;
                    if(registry instanceof SingletonBeanRegistry){
                        SingletonBeanRegistry singletonBeanRegistry = (SingletonBeanRegistry)registry;
                        singletonBeanRegistry.registerSingleton(beanName,proxy);
                    }

//                    registBeanByFactoryBean(beanName,proxy,restClientClass,registry);
                });


    }
    public static void registBeanByFactoryBean(String beanName,Object proxy,Class<?> restClientClass,BeanDefinitionRegistry registry){
        BeanDefinition beanDefinition = null;

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(RestClientClassFactoryBean.class);

        beanDefinitionBuilder.addConstructorArgValue(proxy);
        beanDefinitionBuilder.addConstructorArgValue(restClientClass);

        beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        registry.registerBeanDefinition(beanName,beanDefinition);
    }

    private static class RestClientClassFactoryBean implements FactoryBean {

        private final Proxy proxy;
        private final Class<?> restClientClass;

        private RestClientClassFactoryBean(Proxy proxy, Class<?> restClientClass) {
            this.proxy = proxy;
            this.restClientClass = restClientClass;
        }

        @Override
        public Object getObject() throws Exception {
            return proxy;
        }

        @Override
        public Class<?> getObjectType() {
            return restClientClass;
        }
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
