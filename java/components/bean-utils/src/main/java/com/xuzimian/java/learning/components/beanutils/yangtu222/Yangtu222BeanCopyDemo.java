package com.xuzimian.java.learning.components.beanutils.yangtu222;

import com.tuyang.beanutils.BeanCopyUtils;
import com.xuzimian.java.learning.components.beanutils.base.BaseBeanUtlis;
import com.xuzimian.java.learning.components.beanutils.base.BeanModelSource;
import com.xuzimian.java.learning.components.beanutils.base.BeanModelTarget;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Yangtu222BeanCopyDemo extends BaseBeanUtlis {

    @Test
    public void testCopy() {
        BeanModelTarget beanModelTarget = BeanCopyUtils.copyBean(BeanModelSource.getExample(), BeanModelTarget.class);
        System.out.print(beanModelTarget);
    }

    @Test
    public void testCopyList() {
        List<BeanModelSource> fromList = new ArrayList<>();
        fromList.add(BeanModelSource.getExample());
        fromList.add(BeanModelSource.getExample());
        fromList.add(BeanModelSource.getExample());
        fromList.add(BeanModelSource.getExample());
        List<BeanModelTarget> toList = BeanCopyUtils.copyList(fromList, BeanModelTarget.class);
        System.out.print(toList);
    }

    /**
     * 测试转换效率
     */
    @Test
    public void testCopyEfficiency() {
        runCopyEfficiency(BeanModelSource.getExample(), s -> BeanCopyUtils.copyBean(s, BeanModelTarget.class));
    }
}
