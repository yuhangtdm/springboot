package com.dity.tag.init;

import com.dity.tag.service.DictItemService;
import com.dity.tag.util.CacheUtil;
import com.dity.tag.util.SpringContextBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 初始化执行方法
 * @author:yuhang
 * @Date:2018/5/21
 */
@Component
public class MainInitRunner implements CommandLineRunner {

    @Autowired
    private DictItemService dictItemService;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("init running");
        initRedis();
        registry(SpringContextBeanFactory.getApplicationContext());
    }

    private void registry(ApplicationContext applicationContext) {
        ConfigurableApplicationContext context = (ConfigurableApplicationContext) applicationContext;
        //Bean的实例工厂
        DefaultListableBeanFactory dbf = (DefaultListableBeanFactory) context.getBeanFactory();
        Class<?> clazz;
        try {
            clazz = Class.forName("com.dity.tag.util.CacheUtil");
            BeanDefinitionBuilder uidefinition = BeanDefinitionBuilder.genericBeanDefinition(clazz);
            uidefinition.addPropertyValue("dictItemService", SpringContextBeanFactory.getBean("dictItemServiceImpl"));
            dbf.registerBeanDefinition("cacheUtil", uidefinition.getBeanDefinition());

            System.out.println("done." + ((CacheUtil)SpringContextBeanFactory.getBean("cacheUtil")).dictItemService);
        } catch (ClassNotFoundException e) {
            System.out.println("done.无需初始化CacheUtil");
        }
    }

    private void initRedis() {
        dictItemService.cacheDict();
    }
}
