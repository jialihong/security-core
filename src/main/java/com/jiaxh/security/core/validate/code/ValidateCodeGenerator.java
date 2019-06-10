package com.jiaxh.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Auther: jiaxh
 * @Date: 2019/6/10 14:14
 */
public interface ValidateCodeGenerator {

    ValidateCode generate(ServletWebRequest request);
}
