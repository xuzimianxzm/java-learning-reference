package com.xuzimian.java.learning.components.beanutils.mica;

import  com.xuzimian.java.learning.components.beanutils.base.BaseBeanUtlis;
import  com.xuzimian.java.learning.components.beanutils.base.BeanModelSource;
import  com.xuzimian.java.learning.components.beanutils.base.BeanModelTarget;
import net.dreamlu.mica.core.utils.$;
import org.junit.jupiter.api.Test;

public class MicaBeanUtilDemo extends BaseBeanUtlis {

    @Test
    public void testCopy() {
        BeanModelTarget beanModelTarget = new BeanModelTarget();
        $.copy(BeanModelSource.getExample(), new BeanModelTarget());
        System.out.print(beanModelTarget);


        beanModelTarget = $.copy(BeanModelSource.getExample(), BeanModelTarget.class);
        System.out.print(beanModelTarget);
    }

    /**
     * 测试转换效率
     */
    @Test
    public void testCopyEfficiencyBy() {
        System.out.println("--------------------object 方式-----------------------");
        runCopyEfficiency(BeanModelSource.getExample(), s -> $.copy(BeanModelSource.getExample(),  new BeanModelTarget()));
    }

    /**
     * 测试转换效率
     */
    @Test
    public void testCopyEfficiencyByClass() {
        System.out.println("--------------------Class 方式-----------------------");
        runCopyEfficiency(BeanModelSource.getExample(), s -> $.copy(BeanModelSource.getExample(),  BeanModelTarget.class));
    }
}
