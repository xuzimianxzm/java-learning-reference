package com.xuzimian.globaldemo.common.utils.file;

import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.IOException;

/**
 * @program: global-demo
 * @description: 文件夹文件地址查询器
 * @author: xzm
 * @create: 2019-08-30 12:11
 **/
public class FolderFilePathFinderDemo {

    @Test
    public void test() throws IOException {
        FolderFileRelativePathFinder finder = new FolderFileRelativePathFinder();
        for (String path : finder.findPath("/", new String[]{"xml", "propertites"},true)) {
            System.out.println( path);
        }
    }
}
