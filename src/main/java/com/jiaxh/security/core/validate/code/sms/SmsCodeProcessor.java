package com.jiaxh.security.core.validate.code.sms;

import com.jiaxh.security.core.properties.SecurityConstants;
import com.jiaxh.security.core.validate.code.ValidateCode;
import com.jiaxh.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Auther: jiaxh
 * @Date: 2019/6/28 11:07
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    /**
     * 短信验证码发送器
     * @param request
     * @param validateCode
     * @throws Exception
     */
    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(),paramName);
        smsCodeSender.send(mobile,validateCode.getCode());
    }
}
