package com.jiaxh.security.core.validate.code;

import java.time.LocalDateTime;

/**
 * @Auther: jiaxh
 * @Date: 2019/6/10 15:15
 */
public class ValidateCode {
    /**
     * 验证码，随机生成，保存到session中
     */
    private String code;

    /**
     * 过期的时间点
     */
    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expireTime);
    }

}
