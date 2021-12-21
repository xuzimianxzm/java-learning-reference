package com.xuzimian.globaldemo.common.utils.file;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;

/**
 * @program: global-demo
 * @description: Apache FileUtils 工具类 Demo
 * @author: xzm
 * @create: 2019-08-30 12:01
 **/
public class ApacheFileUtilsDemo {


    @Test
    public void testListFiles() {
        File file = new File(this.getClass().getResource("/").getPath());

        String[] extensions = {"xml", "propertites"};
        for (File temp : FileUtils.listFiles(file, extensions, true)) {
            System.out.println(temp.getPath()+ " : "+ temp.getName());
        }
    }
}
