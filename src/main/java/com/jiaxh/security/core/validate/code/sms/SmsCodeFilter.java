package com.jiaxh.security.core.validate.code.sms;

import com.jiaxh.security.core.properties.SecurityProperties;
import com.jiaxh.security.core.validate.code.ValidateCode;
import com.jiaxh.security.core.validate.code.ValidateCodeException;
import com.jiaxh.security.core.validate.code.ValidateCodeProcessor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 短信验证码过滤器，参照图形验证码过滤器
 */
public class SmsCodeFilter extends OncePerRequestFilter implements InitializingBean {

    //存储需要图形验证的路径
    private Set<String> urls = new HashSet<>();

    private SecurityProperties securityProperties;

    //路径是否匹配工具类
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    public void afterPropertiesSet() throws ServletException {
        //先调父类的方法
        super.afterPropertiesSet();
        //将配置文件里的urls以逗号分隔
        final String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getSms().getUrl(), ",");
        for (String configUrl : configUrls) {
            urls.add(configUrl);
        }
        urls.add("/authentication/mobile");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean action = false;
        for (String url : urls) {
            if(pathMatcher.match(url,request.getRequestURI())){
                action = true;
            }
        }
//        if(StringUtils.equals("/authentication/form",request.getRequestURI()) && StringUtils.endsWithIgnoreCase(request.getMethod(),"POST")){
        if(action){
            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }
        filterChain.doFilter(request,response);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        ValidateCode codeInSession = (ValidateCode)sessionStrategy.getAttribute(request, ValidateCodeProcessor.SESSION_KEY_PREFIX+"SMS");
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),"smsCode");
        if(StringUtils.isBlank(codeInRequest)){
            throw new ValidateCodeException("验证码的值不能为空");
        }
        if(codeInSession == null){
            throw new ValidateCodeException("验证码不存在");
        }
        if(codeInSession.isExpired()){
            sessionStrategy.removeAttribute(request,ValidateCodeProcessor.SESSION_KEY_PREFIX+"SMS");
            throw new ValidateCodeException("验证码已过期");
        }
        if(!StringUtils.equals(codeInSession.getCode(),codeInRequest)){
            throw new ValidateCodeException("验证码不正确");
        }
        //清除session中的验证码
        sessionStrategy.removeAttribute(request,ValidateCodeProcessor.SESSION_KEY_PREFIX+"SMS");
    }

    public Set<String> getUrls() {
        return urls;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    public AntPathMatcher getPathMatcher() {
        return pathMatcher;
    }

    public void setPathMatcher(AntPathMatcher pathMatcher) {
        this.pathMatcher = pathMatcher;
    }

    public SessionStrategy getSessionStrategy() {
        return sessionStrategy;
    }

    public void setSessionStrategy(SessionStrategy sessionStrategy) {
        this.sessionStrategy = sessionStrategy;
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }
}
