package com.chandler.config.server;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 类功能描述
 *
 * @author 钱丁君-chandler 2022/1/12 3:12 下午
 * @since 1.8
 */
public class ApplicationContextAwareTest implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //读取资源
        Resource resource = applicationContext.getResource("");
        //获取配置
        Environment environment = applicationContext.getEnvironment();
        //获取Spring容器
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
        //根据注解获取被修饰的beans
        Map<String, Object> restControllerMap = applicationContext.getBeansWithAnnotation(RestController.class);
        //根据接口获取实现的beans
        Map<String, LoadBalanced> balancedMap = applicationContext.getBeansOfType(LoadBalanced.class);
        //根据接口获取实现的beans，ObjectProvider
        ObjectProvider<LoadBalanced> provider = applicationContext.getBeanProvider(LoadBalanced.class);
    }
}
