package com.xuzimian.globaldemo.common.basic;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

/**
 * @program: global-demo
 * @description:
 * @author: xzm
 * @create: 2019-03-01 12:34
 **/
public class StringDemo {

    @Test
    public void testMessgaeFomart() {
        String msgTemplate = "{0}日递延费数据，黄金延期{1}；迷你黄金延期{2}；白银延期{3}。";
        System.out.println(MessageFormat.format(msgTemplate, 1, "----"));
    }


    @Test
    public void testSubstring() {
        String val1 = "20190506";
        System.out.println(val1.substring(6));
        System.out.println(Integer.valueOf(val1.substring(6)));
    }

    @Test
    public void testStringSplit() {
        String str = "a,b,c";
        List<String> result = Arrays.asList(str.split(","));
        System.out.println(result);

        String[] strArray = "".split(",");
        System.out.println(strArray.length);

        strArray = "a".split(",");
        System.out.println(strArray[0]);
    }

    @Test
    public void testStringUtilsSplit() {
        String str = "a,b,c";
        System.out.println(Arrays.asList(StringUtils.split("a,b,c")));

        System.out.println(Arrays.asList(StringUtils.split("")));
    }

    @Test
    public void testGoogleSplitterSplit() {
        String str = "a,b,c";
        System.out.println(Lists.newArrayList(Splitter.on(',').trimResults().omitEmptyStrings().split(str)));

        System.out.println(Lists.newArrayList(Splitter.on(',').trimResults().omitEmptyStrings().split("")));
    }

    @Test
    public void testObjectAndIntConvertToString() {
        Object intVal1 = 1;
        Object intVal2 = Integer.valueOf(2);

        System.out.println(String.valueOf(intVal1));
        System.out.println(String.valueOf(intVal2));
    }
}
