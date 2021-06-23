package com.xuzimian.java.learning.components.beanutils.hutool;

import cn.hutool.core.bean.BeanUtil;
import  com.xuzimian.java.learning.components.beanutils.base.BaseBeanUtlis;
import  com.xuzimian.java.learning.components.beanutils.base.BeanModelSource;
import  com.xuzimian.java.learning.components.beanutils.base.BeanModelTarget;
import org.junit.jupiter.api.Test;

public class HutoolBeanUtilDemo extends BaseBeanUtlis {

    @Test
    public void testCopy() {
        BeanModelTarget beanModelTarget = new BeanModelTarget();
        BeanUtil.copyProperties(BeanModelSource.getExample(), beanModelTarget);
        System.out.print(beanModelTarget);
    }

    /**
     * 测试转换效率
     */
    @Test
    public void testCopyEfficiency() {
        runCopyEfficiency(BeanModelSource.getExample(), s -> BeanUtil.copyProperties(s, new BeanModelTarget() ));
    }
}
