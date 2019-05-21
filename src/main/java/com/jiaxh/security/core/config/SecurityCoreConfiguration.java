package com.jiaxh.security.core.config;

import com.jiaxh.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: jiaxh
 * @Date: 2019/5/20 11:42
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfiguration  {
}
