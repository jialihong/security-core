package com.jiaxh.security.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * Api接口的实现类
 * QQImpl必须是一个多实例的类，否则会出现线程安全的问题。
 * @Auther: jiaxh
 * @Date: 2019/6/28 16:27
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    private static final Logger logger = LoggerFactory.getLogger(QQImpl.class);

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 获取openId的url
     */
    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    /**
     * 获取用户信息的url
     */
    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    /**
     * 当前系统向QQ方申请的用于QQ登录的唯一Id，一般写在配置文件中
     */
    private String appId;

    /**
     * 获取的openId
     */
    private String openId;

    public QQImpl(String accessToken, String appId) {
        //accessToken交由父类进行处理，以参数的形式自动挂在请求的url上
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);
        logger.info("QQImpl构造方法中的getRestTemplate().getForObject(url, String.class) = [ {} ]",result);

        //截取openId
        this.openId = StringUtils.substringBetween(result,"\"openid\":\"","\"}");
    }

    @Override
    public QQUserInfo getUserInfo() {

        String url = String.format(URL_GET_USERINFO, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);

        System.out.println(result);
        logger.info("getUserInfo()的getRestTemplate().getForObject(url, String.class) = [ {} ]",result);

        QQUserInfo userInfo = null;
        try {
            userInfo = objectMapper.readValue(result,QQUserInfo.class);
            userInfo.setOpenId(openId);
            return userInfo;
        } catch (IOException e) {
            throw new RuntimeException("获取用户信息失败", e);
        }
    }
}
