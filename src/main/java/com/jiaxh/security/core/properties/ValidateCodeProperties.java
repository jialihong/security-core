package com.jiaxh.security.core.properties;

/**
 * @Auther: jiaxh
 * @Date: 2019/5/17 15:56
 */
public class ValidateCodeProperties {
    private ImageCodeProperties image = new ImageCodeProperties();

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }
}
