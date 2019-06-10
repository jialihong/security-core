package com.jiaxh.security.core.validate.code;


import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 图片验证码
 */
public class ImageCode extends ValidateCode{
    /**
     * 图片，根据code生成
     */
    private BufferedImage image;
    /**
     *
     * @param image
     * @param code
     * @param expire 过期时间，以秒为单位，比如60s过期
     */
    public ImageCode(BufferedImage image, String code, int expire) {
        super(code,expire);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code,expireTime);
        this.image = image;
}

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
