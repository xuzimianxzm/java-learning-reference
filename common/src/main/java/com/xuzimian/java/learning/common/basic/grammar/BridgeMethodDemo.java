package com.xuzimian.globaldemo.common.basic.grammar;

import org.junit.Test;

/**
 * Bridge Method  是Java里的一个概念。
 * @program: global-demo
 * @description: Bridge Method  桥接方法演示
 * @author: xzm
 * @create: 2019-06-10 10:49
 **/
public class BridgeMethodDemo {

    abstract class C<T>{
        abstract  T id(T x);
    }

    class D extends  C<String>{

        @Override
        String id(String x) {
            return x;
        }
    }

    /**
     * 在C类中定义了泛型，子类D中给泛型设置了S天ring，使用时新建了D类型的C，调用时由于C中泛型
     * 没有指定具体的类型，所以可以给它的id方法传入任意类型的参数，所以该方法中传如的Object类型
     * 参数编译并没有问题，但实际使用的是D类型的实例，D给泛型设置了String,这在运行时就出错了。
     */
    @Test
    public void testError(){
        C c =new D();
        c.id(new Object());
    }

    @Test
    public void testRight(){
        C c =new D();
        c.id("sss");
    }
}
