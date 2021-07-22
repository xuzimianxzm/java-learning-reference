package com.xuzimian.java.learning.components.beanutils.base;


import java.util.function.Consumer;

public class BaseBeanUtlis {

    public void runCopyEfficiency(BeanModelSource s,Consumer<BeanModelSource> beanUtils) {
        int n = 100;
        Long startTime;

        System.out.print("测试" + n + "次时间:");
        startTime = System.currentTimeMillis();
        for (; n > 0; n--) {
            beanUtils.accept(s);
        }
        System.out.print((System.currentTimeMillis() - startTime) + "毫秒");

        System.out.println();
        n = 10000;
        System.out.print("测试" + n + "次时间:");
        startTime = System.currentTimeMillis();
        for (; n > 0; n--) {
            beanUtils.accept(s);
        }
        System.out.print((System.currentTimeMillis() - startTime) + "毫秒");

        System.out.println();
        n = 1000000;
        System.out.print("测试" + n + "次时间:");
        startTime = System.currentTimeMillis();
        for (; n > 0; n--) {
            beanUtils.accept(s);
        }
        System.out.print((System.currentTimeMillis() - startTime) + "毫秒");

        System.out.println();
        n = 10000000;
        System.out.print("测试" + n + "次时间:");
        startTime = System.currentTimeMillis();
        for (; n > 0; n--) {
            beanUtils.accept(s);
        }
        System.out.print((System.currentTimeMillis() - startTime) + "毫秒");
    }
}
