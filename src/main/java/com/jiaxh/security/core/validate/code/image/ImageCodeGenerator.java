package com.jiaxh.security.core.validate.code.image;

import com.jiaxh.security.core.properties.SecurityProperties;
import com.jiaxh.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @Auther: jiaxh
 * @Date: 2019/6/10 14:29
 */
public class ImageCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ImageCode generate(ServletWebRequest request) {
//        int width = 67;
//        int height = 23;
        //获取配置信息  先从request中获取名称为width的值，若请求中没有，则使用配置文件中的
        int width = ServletRequestUtils.getIntParameter(request.getRequest(),"width",securityProperties.getCode().getImage().getWidth());
        int height = ServletRequestUtils.getIntParameter(request.getRequest(),"height",securityProperties.getCode().getImage().getHeight());
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
        Graphics graphics = bufferedImage.getGraphics();
        Random random = new Random();
        graphics.setColor(getRandColor(200,250));
        graphics.fillRect(0,0,width,height);
        graphics.setFont(new Font("Time New Roman",Font.ITALIC,20));
        graphics.setColor(getRandColor(160,200));
        for (int i = 0;i<155;i++){
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            graphics.drawLine(x,y,xl,yl);
        }

        String sRand = "";
        //从配置文件中获取长度
        for (int i= 0; i < securityProperties.getCode().getImage().getLength(); i++){
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            graphics.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
            graphics.drawString(rand,13 * i + 6,16);
        }
        graphics.dispose();
        //获取过期时间
        return new ImageCode(bufferedImage,sRand,securityProperties.getCode().getImage().getExpireIn());
    }

    /**
     * 给定范围获得随机颜色
     * @param i
     * @param j
     * @return
     */
    private Color getRandColor(int i, int j) {
        Random random = new Random();
        if(i>255) i=255;
        if(j>255) j=255;
        int r=i+random.nextInt(j-i);
        int g=i+random.nextInt(j-i);
        int b=i+random.nextInt(j-i);
        return new Color(r,g,b);
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
