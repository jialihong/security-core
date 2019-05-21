package com.jiaxh.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @Auther: jiaxh
 * @Date: 2019/5/7 15:26
 */
@ConfigurationProperties(prefix = "jiaxh.security")
public class SecurityProperties {
    /**
     * 坑坑
     * field名字必须与配置文件中的 jiaxh.security.browser.loginPage必须完全相同才能匹配到
     */
    private BrowserProperties browser = new BrowserProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
