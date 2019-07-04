package com.jiaxh.security.core.social.qq.connet;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * @Auther: jiaxh
 * @Date: 2019/7/4 14:02
 * 自定义一个OAuth2Operations的实现类，替换掉默认的OAuth2Template.
 * 1、默认的OAuth2Template的消息处理器只有3种，无法处理QQ服务提供商返回的text/html格式的contentType
 * --> 在自定义的OAuth2Operations实现类中，重写createRestTemplate(),多加一个messageConverter，用来处理text/html响应的contentType
 *
 * 2、默认的OAuth2Template的处理返回值的方式是期望将json格式的返回值转换为map，但是QQ服务提供商的返回值是string类型的，并通过&连接在一起
 * --> 在自定义的OAuth2Operations实现类中，重写OAuth2Template的postForAccessGrant()，解析QQ服务提供商的通过&连接的string类型的返回值
 */
public class QQOAuth2Template extends OAuth2Template {

    private Logger logger = LoggerFactory.getLogger(QQOAuth2Template.class);

    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        // QQ互联接口文档显示，获取access_token有5个必填参数，
        // 但是在exchangeForAccess()方法中，只有当userParametersForClientAuthentication属性值为true时，才设置client_id、client_secret
        setUseParametersForClientAuthentication(true);
    }

    /**
     * 将请求格式按照QQ标准进行自定义解析
     * @param accessTokenUrl
     * @param parameters
     * @return
     */
    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        String responseStr = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
        logger.info("向QQ服务提供商获取AccessToken的响应: {}", responseStr);

        String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(responseStr, "&");

        String accessToken = StringUtils.substringAfterLast(items[0],"=");
        Long expireIn = new Long(StringUtils.substringAfterLast(items[1],"="));
        String refreshToken = StringUtils.substringAfterLast(items[2],"=");

        return new AccessGrant(accessToken,null,refreshToken,expireIn);
    }

    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        //增加一个StringHttpMessageConverter，用来处理text/html响应的contentType
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }


}
