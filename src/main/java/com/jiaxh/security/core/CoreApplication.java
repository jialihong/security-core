package com.jiaxh.security.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Auther: jiaxh
 * @Date: 2019/5/17 15:37
 */
@SpringBootApplication(scanBasePackages = {"com.jiaxh.security"})
public class CoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }

}
