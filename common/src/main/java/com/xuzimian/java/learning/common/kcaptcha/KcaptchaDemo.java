package com.xuzimian.globaldemo.common.kcaptcha;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.text.impl.ChineseTextProducer;
import com.google.code.kaptcha.util.Config;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Properties;

/**
 * 随机图片生成工具，google开发
 */
public class KcaptchaDemo {

    @Test
    public void testDefaultKaptchaCreateText() {
        DefaultKaptcha captchaProducer = getDefaultKaptcha();

        for (int n = 0; n < 100; n++) {
            System.out.println(captchaProducer.createText());
        }
    }

    @Test
    public void testChineseTextProducerCreateText() {
        ChineseTextProducer producer = new ChineseTextProducer();

        for (int n = 0; n < 100; n++) {
            System.out.println(producer.getText());
        }
    }



    @Test
    public void testDefaultKaptchaCreateImage() throws IOException {
        DefaultKaptcha captchaProducer = getDefaultKaptcha();

        BufferedImage image=  captchaProducer.createImage("123456");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", out);
        File file = new File("C:\\Users\\AW\\Desktop\\123456.jpg");
        OutputStream os = new FileOutputStream(file);
        os.write(out.toByteArray());
    }

    private DefaultKaptcha getDefaultKaptcha() {
        DefaultKaptcha captchaProducer = new DefaultKaptcha();
        captchaProducer.setConfig(new Config(new Properties()));
        return captchaProducer;
    }
}
