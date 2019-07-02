package com.jiaxh.security.core.social.qq.config;

import com.jiaxh.security.core.properties.QQProperties;
import com.jiaxh.security.core.properties.SecurityProperties;
import com.jiaxh.security.core.social.qq.connet.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @Auther: jiaxh
 * @Date: 2019/7/2 14:12
 */
@Configuration
//只有在配置文件中配置了前缀为jiaxh.security.social.qq，名称为app-id的配置项时，以下配置才生效
@ConditionalOnProperty(prefix = "jiaxh.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqProperties = securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qqProperties.getProvidedId(), qqProperties.getAppId(), qqProperties.getAppSecret());
    }
}
