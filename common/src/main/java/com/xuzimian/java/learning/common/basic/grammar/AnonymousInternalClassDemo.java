package com.xuzimian.globaldemo.common.basic.grammar;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

/**
 * 匿名内部类问题
 */
public class AnonymousInternalClassDemo {
    String objectStr = StringUtils.EMPTY;

    @Test
    public void test() {
        String str = StringUtils.EMPTY;

        class InnerClass{
            @Override
            public String toString() {
                //str="";
                return str;
            }
        }
    }

    public void show(){
        int a = 1;
        (new Object(){
            public void doChange(){
                System.out.println(a);//jdk1.8之前编译报错，1.8之后，不报错，因为没有改变变量的值
            }
        }).doChange();
        System.out.println(a);
    }
}
