package com.jiaxh.security.core.social.qq;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @Auther: jiaxh
 * @Date: 2019/7/4 11:22
 * 将/auth路径做成可配置的
 * 自定义过滤器，重写postProcess()
 */
public class JiaSpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessUrl;

    public JiaSpringSocialConfigurer(String filterProcessUrl) {
        this.filterProcessUrl = filterProcessUrl;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter)super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessUrl);
        return (T) filter;
    }
}
