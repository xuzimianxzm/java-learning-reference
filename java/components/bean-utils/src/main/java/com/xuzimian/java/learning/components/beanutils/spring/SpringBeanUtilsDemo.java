package com.xuzimian.java.learning.components.beanutils.spring;

import com.xuzimian.java.learning.components.beanutils.base.BaseBeanUtlis;
import com.xuzimian.java.learning.components.beanutils.base.BeanModelSource;
import com.xuzimian.java.learning.components.beanutils.base.BeanModelTarget;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

public class SpringBeanUtilsDemo extends BaseBeanUtlis {

    @Test
    public void testCopy() {
        BeanModelTarget beanModelTarget = new BeanModelTarget();
        BeanUtils.copyProperties(BeanModelSource.getExample(), beanModelTarget);
        System.out.print(beanModelTarget);
    }

    /**
     * 测试转换效率
     */
    @Test
    public void testCopyEfficiency() {
        runCopyEfficiency(BeanModelSource.getExample(), s -> BeanUtils.copyProperties(s, new BeanModelTarget() ));
    }

}
