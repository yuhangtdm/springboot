package com.dity.init;

import org.springframework.boot.CommandLineRunner;

/**
 * @author:yuhang
 * @Date:2018/4/8
 */
public class MainInitRunner implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {
        System.out.println("springboot容器初始化之前我们可以做的事情");
    }
}
