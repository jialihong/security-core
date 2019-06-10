package com.jiaxh.security.core.validate.code.sms;

/**
 * @Auther: jiaxh
 * @Date: 2019/6/10 15:25
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机"+mobile+"发送短信验证码"+code);
    }
}
