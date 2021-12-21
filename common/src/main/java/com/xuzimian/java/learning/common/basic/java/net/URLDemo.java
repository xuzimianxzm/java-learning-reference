package com.xuzimian.globaldemo.common.basic.java.net;

import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

/**
 * @program: global-demo
 * @description: URL Demo
 * @author: xzm
 * @create: 2019-08-30 11:11
 **/
public class URLDemo {


    /**
     * 测试class的getResource 资源路径
     */
    @Test
    public void testURL() {
        Optional<URL> urlOpt = Optional.ofNullable(this.getClass().getResource("/config/"));
        urlOpt.ifPresent(url->{
            System.out.println(url.getFile());
            System.out.println(url.getPath());
            System.out.println(url.getRef());
            System.out.println(url.getUserInfo());
            try {
                System.out.println(url.getContent());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
