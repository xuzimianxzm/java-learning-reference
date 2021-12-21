package com.xuzimian.globaldemo.common.basic.java.util.function;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @program: autd-java
 * @description:  BiConsumer lambda表达式
 * @author: xzm
 * @create: 2019-06-18 10:55
 **/
public class BiConsumerDemo {


    /**
     * BiConsumer lambda表达式 推到
     */
    @Test
    public void testBiConsumer() {

        //1.function  完整书写模式
        BiConsumer<Map<String, Object>, String> doSomething = new BiConsumer<Map<String, Object>, String>() {
            @Override
            public void accept(Map<String, Object> stringObjectMap, String s) {
                stringObjectMap.put("key", s);
            }
        };

        Map<String, Object> result = Maps.newHashMapWithExpectedSize(3);

        doSomething.accept(result, "hello world");

        System.out.println(result);

        //2.简写 使用——lambda
        doSomething = (Map<String, Object> m, String s) -> {
            m.put("key-1", s);
        };
        doSomething.accept(result, "hello world -1");
        System.out.println(result);

        //3.简写 使用——lambda
        doSomething = (m, s) -> m.put("key-2", s);
        doSomething.accept(result, "hello world -2");
        System.out.println(result);
    }

    @Test
    public void testCheckAppVersionByLinkAddress() {
        Map<String, String> result = Maps.newHashMapWithExpectedSize(3);

        result.put("link_address", "{protogenesis:1}");

        checkAppVersionByLinkAddress(result,
                version -> StringUtils.isEmpty(version),
                resultByVersion -> resultByVersion.get("versions"),
                resultByLinkAddress -> resultByLinkAddress.get("link_address"),
                (m, v) -> m.put("link_address", v),
                "http://baidu.com"
        );

        System.out.println(result.get("link_address"));
    }

    /**
     * @param t              加工对象类型，最终为该对象设置正确的字段值，可以是一个Map,也可以是一个JavaBean
     * @param checkVersion   Predicate 表达式，动态的检查条件，主要用于检查版本字段是否为空
     * @param getVersion     Function 表达式，动态获取版本值，用于前一个参数的动态检查条件中，从某个对象上，可以是一个Map,也可以是一个JavaBean。
     * @param getLinkAddress Function 表达式，动态获取属性值，从某个对象上，可以是一个Map,也可以是一个JavaBean。
     * @param setLinkAddress BiConsumer 表达式，动态设置一个String 值到某个对象上。
     * @param appUpdateH5    一个String值，用于给前一个表达式用于设置到某个对象上去。
     * @param <T>
     */
    public <T> void checkAppVersionByLinkAddress(T t, Predicate<String> checkVersion,
                                                 Function<T, String> getVersion,
                                                 Function<T, String> getLinkAddress,
                                                 BiConsumer<T, String> setLinkAddress,
                                                 String appUpdateH5) {
        if (checkVersion.test(getVersion.apply(t))) {
            if (getLinkAddress.apply(t).contains("protogenesis")) {
                //获取App升级h5地址
                setLinkAddress.accept(t, appUpdateH5);
            }
        }
    }
}
