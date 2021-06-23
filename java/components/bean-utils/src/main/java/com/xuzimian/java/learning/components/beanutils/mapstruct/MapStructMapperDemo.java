package com.xuzimian.java.learning.components.beanutils.mapstruct;

import  com.xuzimian.java.learning.components.beanutils.base.BaseBeanUtlis;
import  com.xuzimian.java.learning.components.beanutils.base.BeanModelSource;
import  com.xuzimian.java.learning.components.beanutils.base.BeanModelTarget;
import org.junit.jupiter.api.Test;

public class MapStructMapperDemo extends BaseBeanUtlis {

    @Test
    public void test() {
        BeanModelTarget model = BeanSourceMapper.INSTANCE.sourceToTarget(BeanModelSource.getExample());
        System.out.println(model);
    }

    /**
     * 测试转换效率
     */
    @Test
    public void testCopyEfficiency() {
       runCopyEfficiency(BeanModelSource.getExample(), (s) ->  BeanSourceMapper.INSTANCE.sourceToTarget(s));
    }
}
