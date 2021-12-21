package com.xuzimian.globaldemo.common.xml.digester;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Digeste 是apache开源项目Commons中的一个子项目，用于解析XML文档的工具。
 * Digester底层采用的是SAX解析方式，通过遍历XML文档规则来进行处理。
 * 项目中有需要将XML文件中的信息解析为我们需要的内容时（如java类），
 * 使用Digester是非常方便的
 *
 * @program: global-demo
 * @description: Digester解析类
 * @author: xzm
 * @create: 2019-05-24 13:43
 **/
public class DigesterDemo {

    public static void main(String[] args) {
        parseByJavaBean();
    }

    /**
     * digester，对于XML文档中每个元素，Digester对象都会检查它是否需要做预定义事件，
     * 所以我们在parse前定义好Digester对象需要执行哪些动作即可
     */
    public static void parseByJavaBean() {
        // 1.初始化Digester实例对象
        Digester digester = new Digester();

        // 2.解析<Order>标签节点
        digester.addObjectCreate("Orders", ArrayList.class);//list进栈，栈顶元素的list对象
        digester.addObjectCreate("Orders/Order", Order.class);//Order实例进栈，栈顶元素时Order实例对象
        digester.addSetProperties("Orders/Order");//设置<Order>标签的属性

        // 3.解析<goods>标签节点
        digester.addObjectCreate("Orders/Order/goods", Goods.class);//Goods实例对象进栈
        digester.addSetProperties("Orders/Order/goods");
        digester.addBeanPropertySetter("Orders/Order/goods/name");//设置<goods>下的其他标签内容
        digester.addBeanPropertySetter("Orders/Order/goods/price");
        digester.addBeanPropertySetter("Orders/Order/goods/count");
        digester.addBeanPropertySetter("Orders/Order/goods/total_price");
        digester.addSetNext("Orders/Order/goods", "add");//Goods对象实例出栈
        digester.addSetNext("Orders/Order", "add");//Order对象实例出栈

        // 4.加载配置文件
       String path= DigesterDemo.class.getClassLoader().getResource("Order.xml").getFile();

        File file =new File(path);

        // 5.解析
        try {
            ArrayList list = (ArrayList) digester.parse(file);
            System.out.println(list.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
