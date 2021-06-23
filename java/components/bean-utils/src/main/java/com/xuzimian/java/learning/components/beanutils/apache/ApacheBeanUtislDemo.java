package com.xuzimian.java.learning.components.beanutils.apache;

import  com.xuzimian.java.learning.components.beanutils.base.BaseBeanUtlis;
import  com.xuzimian.java.learning.components.beanutils.base.BeanModelSource;
import  com.xuzimian.java.learning.components.beanutils.base.BeanModelTarget;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

public class ApacheBeanUtislDemo  extends BaseBeanUtlis {
    @Test
    public void testCopy() throws InvocationTargetException, IllegalAccessException {
        BeanModelTarget beanModelTarget = new BeanModelTarget();
        BeanUtils.copyProperties(BeanModelSource.getExample(), beanModelTarget);
        System.out.print(beanModelTarget);
    }

    /**
     * 测试转换效率
     */
    @Test
    public void testCopyEfficiency() {


        ConvertUtils.register(new Converter() {
            @Override
            public Object convert(Class type, Object value) {
                return new Date();
            }
        }, Date.class);

        runCopyEfficiency(BeanModelSource.getExample(),s-> {
            try {
                BeanUtils.copyProperties(s,new BeanModelTarget());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }
}
