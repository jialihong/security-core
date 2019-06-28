package com.jiaxh.security.core.social.qq.connet;

import com.jiaxh.security.core.social.qq.api.QQ;
import com.jiaxh.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * 适配器的泛型是指 ：  当前适配器所要适配的Api的类型是什么
 * @Auther: jiaxh
 * @Date: 2019/6/28 17:29
 */
public class QQAdapter implements ApiAdapter<QQ> {
    @Override
    public boolean test(QQ api) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserInfo userInfo = api.getUserInfo();

        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        //服务提供商为QQ时，该属性设置为null即可，因为QQ没有类似个人主页的东西
        values.setProfileUrl(null);
        //openId为用户在服务提供商的唯一标识
        values.setProviderUserId(userInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {

    }
}
