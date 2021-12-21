package com.xuzimian.globaldemo.common.basic.grammar;

import org.junit.Test;

/**
 * @program: global-demo
 * @description: 方法签名测试
 * @author: xzm
 * @create: 2019-05-16 09:43
 **/
public class MethodSignatureDemo {

    @Test
    public void testMethodSignature() {
        get(1);
    }

    public void get(int arg) {
        System.out.println(" int :" + arg);
    }

    public void get(Integer arg) {
        System.out.println(" Integer :" + arg);
    }


    public void get(long arg) {
        System.out.println(" long :" + arg);
    }

    public void get(Long arg) {
        System.out.println(" Long :" + arg);
    }

    public void get(char arg) {
        System.out.println(" int :" + arg);
    }

    public void get(Character arg) {
        System.out.println(" Character :" + arg);
    }


    public void get(float arg) {
        System.out.println(" float :" + arg);
    }

    public void get(Float arg) {
        System.out.println(" Float :" + arg);
    }

    public void get(double arg) {
        System.out.println(" double :" + arg);
    }

    public void get(Double arg) {
        System.out.println(" Double :" + arg);
    }

    public void get(String arg) {
        System.out.println(" String :" + arg);
    }

}
