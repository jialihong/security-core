package com.jiaxh.security.core.authentication.mobile;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

/**
 * 短信验证码登录配置
 * SmsCodeAuthenticationToken
 * SmsCodeAuthenticationFilter
 * SmsCodeAuthenticationProvider
 * 短信验证码过滤器 SmsCodeFilter
 * 将以上4个组件组合起来
 */
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {


}
