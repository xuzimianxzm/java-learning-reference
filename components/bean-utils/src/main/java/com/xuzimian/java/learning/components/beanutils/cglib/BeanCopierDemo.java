package com.xuzimian.java.learning.components.beanutils.cglib;

import  com.xuzimian.java.learning.components.beanutils.base.BaseBeanUtlis;
import  com.xuzimian.java.learning.components.beanutils.base.BeanModelSource;
import  com.xuzimian.java.learning.components.beanutils.base.BeanModelTarget;
import net.sf.cglib.beans.BeanCopier;
import org.junit.jupiter.api.Test;

public class BeanCopierDemo extends BaseBeanUtlis {

    @Test
    public void test() {
        final BeanCopier copier = BeanCopier.create(BeanModelSource.class, BeanModelTarget.class, false);
        BeanModelTarget beanModelTarget = new BeanModelTarget();
        copier.copy(BeanModelSource.getExample(), beanModelTarget, null);
        System.out.println(beanModelTarget);
    }

    /**
     * 测试转换效率
     */
    @Test
    public void testCopyEfficiency() {
        final BeanCopier copier = BeanCopier.create(BeanModelSource.class, BeanModelTarget.class, false);
        runCopyEfficiency(BeanModelSource.getExample(), (s) -> copier.copy(s, new BeanModelTarget(), null));
    }

    /**
     * 测试重复创建BeanCopier时转换效率
     */
    @Test
    public void testRepeatCreateBeanCopierCopyEfficiency() {
        runCopyEfficiency(BeanModelSource.getExample(), (s) -> {
            BeanCopier copier = BeanCopier.create(BeanModelSource.class, BeanModelTarget.class, false);
            copier.copy(s, new BeanModelTarget(), null);
        });

    }
}
