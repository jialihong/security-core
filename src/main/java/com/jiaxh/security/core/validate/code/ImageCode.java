package com.jiaxh.security.core.validate.code;


import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 图片验证码
 */
public class ImageCode {
    /**
     * 图片，根据code生成
     */
    private BufferedImage image;
    /**
     * 验证码，随机生成，保存到session中
     */
    private String code;
    /**
     * 过期的时间点
     */
    private LocalDateTime expireTime;

    /**
     *
     * @param image
     * @param code
     * @param expire 过期时间，以秒为单位，比如60s过期
     */
    public ImageCode(BufferedImage image, String code, int expire) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expire);
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expireTime);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
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
}
