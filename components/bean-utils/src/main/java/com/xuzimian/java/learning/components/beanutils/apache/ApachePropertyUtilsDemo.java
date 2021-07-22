package com.xuzimian.java.learning.components.beanutils.apache;

import com.xuzimian.java.learning.components.beanutils.base.BaseBeanUtlis;
import com.xuzimian.java.learning.components.beanutils.base.BeanModelSource;
import com.xuzimian.java.learning.components.beanutils.base.BeanModelTarget;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

public class ApachePropertyUtilsDemo extends BaseBeanUtlis {

    @Test
    public void testCopy() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        BeanModelTarget beanModelTarget = new BeanModelTarget();
        PropertyUtils.copyProperties(BeanModelSource.getExample(), beanModelTarget);
        System.out.print(beanModelTarget);
    }

    /**
     * 测试转换效率
     */
    @Test
    public void testCopyEfficiency() {
        runCopyEfficiency(BeanModelSource.getExample(), s -> {
            try {
                PropertyUtils.copyProperties(s, new BeanModelTarget());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        });
    }
}
