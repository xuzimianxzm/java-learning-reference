package com.xuzimian.java.learning.components.beanutils.dozer;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import  com.xuzimian.java.learning.components.beanutils.base.BaseBeanUtlis;
import  com.xuzimian.java.learning.components.beanutils.base.BeanModelSource;
import  com.xuzimian.java.learning.components.beanutils.base.BeanModelTarget;
import org.junit.jupiter.api.Test;

public class DozerBeanMapperDemo extends BaseBeanUtlis {

    @Test
    public void testCopy() {
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        BeanModelTarget beanModelTarget = mapper.map(BeanModelSource.getExample(), BeanModelTarget.class);
        System.out.print(beanModelTarget);
    }

    /**
     * 测试转换效率
     */
    @Test
    public void testCopyEfficiency() {
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        runCopyEfficiency(BeanModelSource.getExample(), s -> mapper.map(s,  BeanModelTarget.class ));
    }
}
