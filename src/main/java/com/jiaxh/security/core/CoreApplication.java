package com.jiaxh.security.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther: jiaxh
 * @Date: 2019/5/17 15:37
 */

@SpringBootApplication
@EnableSwagger2
@EnableConfigurationProperties
public class CoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }

}
