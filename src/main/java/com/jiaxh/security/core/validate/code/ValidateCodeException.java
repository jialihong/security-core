package com.jiaxh.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * 自定义校验异常
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
