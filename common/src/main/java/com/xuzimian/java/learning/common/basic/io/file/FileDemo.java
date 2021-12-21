package com.xuzimian.globaldemo.common.basic.io.file;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * @program: global-demo
 * @description: File Demo
 * @author: xzm
 * @create: 2019-08-30 11:21
 **/
public class FileDemo {


    /**
     * 测试File
     */
    @Test
    public void testFile() {
        Optional<URL> urlOpt = Optional.ofNullable(this.getClass().getResource("/config/"));
        urlOpt.ifPresent(url -> {
            File file = new File(url.getPath());
            System.out.println(file.isDirectory());
            System.out.println(file.isFile());
            System.out.println(file.getName());
            System.out.println(file.getParent());
            System.out.println(file.getPath());
        });
    }

    @Test
    public void testGetResouce() {
        File file = new File(
                getClass().getClassLoader().getResource("/config/").getFile()
        );
        System.out.println(file);
    }

    @Test
    public void testGetResourceAsStream() {
        InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream("/config/");

    }

    @Test
    public void loadPropertitesTest() {

        ClassLoader classLoader = getClass().getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream("/dev/env.propertites")) {

            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            System.out.println(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
