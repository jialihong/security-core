package com.jiaxh.security.core.properties;

/**
 * @Auther: jiaxh
 * @Date: 2019/5/7 15:27
 */
public class BrowserProperties {
    //默认值
    private String loginPage = "/jia-login.html";

    private LoginType loginType = LoginType.JSON;

    /**
     * 设置记住我的过期时间
     */
    private int rememberMeSeconds = 3600;

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}
