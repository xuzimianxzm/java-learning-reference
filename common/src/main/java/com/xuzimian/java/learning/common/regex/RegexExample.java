package com.xuzimian.java.learning.common.regex;


import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {

    @Test
    public  void example0() {
        String REGEX = "\\bcat\\b";
        String INPUT = "cat cat cat cattie cat";
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(INPUT); // 获取 matcher 对象
        int count = 0;

        while (m.find()) {
            count++;
            System.out.println("Match number " + count);
            System.out.println("start(): " + m.start());
            System.out.println("end(): " + m.end());
            System.out.println("group(): " + m.group());

        }
    }

    @Test
    public  void example1() {
        String REGEX = "\\$\\{\\S*\\}";
        String INPUT = "ss$ss ss{ ${对象号} }sss${表名}s";
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(INPUT);

        while (m.find()) {
            System.out.println("group(): " + m.group());
        }
    }

    @Test
    public  void example2() {
        String str = "this is \"Tom\" and \"Eric\"， this is \"Bruce lee\", he is a chinese, name is \"李小龙\"。";
        Pattern p = Pattern.compile("\"(.*?)\"");
        Matcher m = p.matcher(str);
        while (m.find()) {
            System.out.println(m.group());
        }
    }

    @Test
    public  void example3() {
        String str = "<?xml version='1.0' encoding='UTF-8'?><ufinterface billtype='gl' filename='e:\1.xml' isexchange='Y' proc='add' receiver='1060337@1060337-003' replace='Y' roottag='sendresult' sender='01' successful='Y'><sendresult><billpk></billpk><bdocid>w764</bdocid><filename>e:\1.xml</filename><resultcode>1</resultcode><resultdescription>单据w764开始处理...单据w764处理完毕!</resultdescription><content>2017.09-记账凭证-1</content></sendresult><sendresult><billpk></billpk><bdocid>w1007</bdocid><filename>e:\1.xml</filename><resultcode>1</resultcode><resultdescription>单据w1007开始处理...单据w1007处理完毕!</resultdescription><content>2017.10-记账凭证-1</content></sendresult><sendresult><billpk></billpk><bdocid>w516</bdocid><filename>e:\1.xml</filename><resultcode>1</resultcode><resultdescription>单据w516开始处理...单据w516处理完毕!</resultdescription><content>2017.07-记账凭证-50</content></sendresult></ufinterface>";
        // String str = "abc3443abcfgjhgabcgfjabc";
        String rgex = "<bdocid>(.*?)</bdocid>";
        Pattern p = Pattern.compile(rgex);
        Matcher m = p.matcher(str);
        while (m.find()) {
            System.out.println("group(): " + m.group());
        }
    }

    @Test
    public  void example4() {
        String REGEX = "\\$\\{(.*?)\\}";
        String INPUT = "ss$ss ss{ ${对象号} }sss${表名}s";
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(INPUT);

        while (m.find()) {
            System.out.println("group(1): " + m.group(1));
        }
    }

    /**
     * 表达式: A.*?B（“.“表示任意字符，“？”表示匹配0个或多个）
     * 示例: Abaidu.comB
     * 结果: Awww.apizl.comB
     * 匹配两个字符串A与B中间的字符串包含A但是不包含B：
     * 表达式: A.*?(?=B)
     * 示例: Awww.apizl.comB
     * 结果: Awww.apizl.com
     * 匹配两个字符串A与B中间的字符串且不包含A与B：
     * 表达式: (?<=A).*?(?=B)
     * （?<=要匹配的开始字符），不包含后面要匹配的字符写法（？=要匹配的结束字符）
     * 示例: Awww.baidu.comB
     * 结果: www.baidu.com
     */
    public void example5() {
        Pattern p = Pattern.compile("(?<=T).*?(?=:00000)");

        Matcher m = p.matcher("18991230T11:00:00000");

        while (m.find()) {
            System.out.println(m.group(0));//m.group(1)不包括这两个字符
        }

        String str = "18991230T11:00:00000";
        p.splitAsStream("18991230T11:00:00000").map(it -> str.replace(it, ""));

        System.out.println("18991230T11:00:00000".split("T")[1].replace(":00000", ""));
    }
}