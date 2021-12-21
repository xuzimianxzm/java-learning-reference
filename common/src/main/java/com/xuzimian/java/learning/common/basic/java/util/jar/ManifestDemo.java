package com.xuzimian.globaldemo.common.basic.java.util.jar;

import java.io.IOException;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * @program: global-demo
 * @description:
 * @author: xzm
 * @create: 2019-08-18 14:45
 **/
public class ManifestDemo {

    public static void main(String[] args) throws IOException {
        String jarPath = "E:\\MyCode\\GlobalDemo\\common\\target\\global-demo-common.jar";
        JarFile jarfile = new JarFile(jarPath);
        Manifest manifest = jarfile.getManifest();
        Attributes att = manifest.getMainAttributes();
        System.out.println(att.getValue("Class-Path"));

    }

}
