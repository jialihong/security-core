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
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
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

    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        //使用JdbcUsersConnectionRepository操作数据库中UserConnection表
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        //在UserConnection表上加前缀，符合公司表名规范
        repository.setTablePrefix("jiaxh_");

        //使用自定义的connectionSignUp,实现第三方登录时自动注册
        if(connectionSignUp != null){
            repository.setConnectionSignUp(connectionSignUp);
        }
        return repository;
    }

    /**
     * 使用自定义的configurer
     * @return
     */
    @Bean
    public SpringSocialConfigurer jiaxhSocialSecurityConfig(){
        //使用自定义的第三方登录回调url
        String filterProcessUrl = securityProperties.getSocial().getFilterProcessUrl();
        JiaSpringSocialConfigurer configurer = new JiaSpringSocialConfigurer(filterProcessUrl);

        //使用自定义的注册页面 过滤器在找不到用户时，跳转到自定义的注册页面
        configurer.signupUrl(securityProperties.getBrowser().getSignUpUrl());
        return configurer;
    }

    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator){
        return new ProviderSignInUtils(connectionFactoryLocator,getUsersConnectionRepository(connectionFactoryLocator)){

        };
    }
}
