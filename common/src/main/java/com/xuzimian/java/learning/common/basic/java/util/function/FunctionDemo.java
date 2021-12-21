package com.xuzimian.globaldemo.common.basic.java.util.function;

import org.junit.Test;

import java.util.function.Function;

/**
 * @program: global-demo
 * @description: Function Demo
 * @author: xzm
 * @create: 2019-09-03 17:03
 **/
public class FunctionDemo {


    /**
     * Function<T,K> 传入一个T类型参数，并返回另一个K类型的结果。
     * 如果采用 d -> d 其相当于如下方式：
     *  Function<String,String> getString =new Function<String,String>() {
     *             @Override
     *             public String apply(String o) {
     *                 return o;
     *             }
     * };
     */
    @Test
    public void test() {
        System.out.println(" d-> '123' :"+isAjaxRequest(d -> "123"));
        System.out.println(" d -> d :"+ isAjaxRequest(d -> d));

        Function<String,String> getString =new Function<String,String>() {
            @Override
            public String apply(String o) {
                return o;
            }
        };

        System.out.println(((Function) getString).apply(" Function<String,String> getString =new Function<String,String>() {\n" +
                "            @Override\n" +
                "            public String apply(String o) {\n" +
                "                return o;\n" +
                "            }\n" +
                "        };"));
    }

    public static boolean isAjaxRequest(Function<String, String> getHeader) {
        System.out.print(getHeader.apply("xzm"));
        return isAjaxRequest(getHeader.apply("xzm"));
    }

    public static boolean isAjaxRequest(String header) {
        if (null == header) {
            return false;
        }

        if ("123".equals(header.toLowerCase())) {
            return true;
        }
        return false;
    }
}
