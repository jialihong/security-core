package com.jiaxh.security.core.validate.code.sms;

/**
 * @Auther: jiaxh
 * @Date: 2019/6/10 15:24
 */
public interface SmsCodeSender {
    void send(String mobile,String code);
}
