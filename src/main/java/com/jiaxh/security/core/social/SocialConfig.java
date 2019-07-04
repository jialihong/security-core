package com.jiaxh.security.core.social;

import com.jiaxh.security.core.properties.SecurityProperties;
import com.jiaxh.security.core.social.qq.JiaSpringSocialConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @Auther: jiaxh
 * @Date: 2019/6/28 17:45
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        //使用JdbcUsersConnectionRepository操作数据库中UserConnection表
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        //在UserConnection表上加前缀，符合公司表名规范
        repository.setTablePrefix("jiaxh_");
        return repository;
    }

    /**
     * 使用自定义的configurer
     * @return
     */
    @Bean
    public SpringSocialConfigurer jiaxhSocialSecurityConfig(){
        String filterProcessUrl = securityProperties.getSocial().getFilterProcessUrl();
        JiaSpringSocialConfigurer configurer = new JiaSpringSocialConfigurer(filterProcessUrl);
        return configurer;
    }
}
