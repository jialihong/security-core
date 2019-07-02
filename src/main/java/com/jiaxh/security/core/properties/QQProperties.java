package com.jiaxh.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @Auther: jiaxh
 * @Date: 2019/7/1 14:33
 */
public class QQProperties extends SocialProperties {

    /**
     * 服务提供商的标识，默认为qq
     */
    private String providedId = "qq";

    public String getProvidedId() {
        return providedId;
    }

    public void setProvidedId(String providedId) {
        this.providedId = providedId;
    }
}
