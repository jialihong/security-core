package com.jiaxh.security.core.properties;

/**
 * @Auther: jiaxh
 * @Date: 2019/7/2 14:09
 */
public class SocialProperties {

    private QQProperties qq = new QQProperties();

    private String filterProcessUrl = "/auth";

    public QQProperties getQq() {
        return qq;
    }

    public void setQq(QQProperties qq) {
        this.qq = qq;
    }

    public String getFilterProcessUrl() {
        return filterProcessUrl;
    }

    public void setFilterProcessUrl(String filterProcessUrl) {
        this.filterProcessUrl = filterProcessUrl;
    }
}
