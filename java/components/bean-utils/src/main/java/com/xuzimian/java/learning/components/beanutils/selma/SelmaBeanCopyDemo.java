package com.xuzimian.java.learning.components.beanutils.selma;

import  com.xuzimian.java.learning.components.beanutils.base.BaseBeanUtlis;
import  com.xuzimian.java.learning.components.beanutils.base.BeanModelSource;
import  com.xuzimian.java.learning.components.beanutils.base.BeanModelTarget;
import fr.xebia.extras.selma.Selma;
import org.junit.jupiter.api.Test;

public class SelmaBeanCopyDemo extends BaseBeanUtlis {

    @Test
    public void testCopy() {
        SelmaMapper mapper = Selma.builder(SelmaMapper.class).build();
        System.out.print(mapper.asTarget(BeanModelSource.getExample()));
    }

    /**
     * 测试转换效率
     */
    @Test
    public void testCopyEfficiency() {
        SelmaMapper mapper = Selma.builder(SelmaMapper.class).build();
        runCopyEfficiency(BeanModelSource.getExample(), s -> mapper.asTarget(s));
        System.out.println("\r\n------------------------------------------------------");
        runCopyEfficiency(BeanModelSource.getExample(), s -> mapper.copy(s, new BeanModelTarget()));
    }
}
