package com.xuzimian.globaldemo.common.basic.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @program: global-demo
 * @description: 注解说明
 * @author: xzm
 * @create: 2019-08-16 10:32
 **/
@CustomAnnotation
public class AnnotationDemo {

    @CustomAnnotation
    private String field = "我是字段";
    @CustomAnnotation("测试方法")
    public void test(@CustomAnnotation String args) {
        System.out.println("参数值 === "+args);
    }
    public static void main(String[] args) {
        // 获取类上的注解MyAnTargetType
        CustomAnnotation t = AnnotationDemo.class.getAnnotation(CustomAnnotation.class);
        System.out.println("类上的注解值 === "+t.value());
        CustomAnnotation tm = null;
        try {
            // 根据反射获取AnnotationTest类上的test方法
            Method method = AnnotationDemo.class.getDeclaredMethod("test",String.class);
            // 获取方法上的注解MyAnTargetMethod
            tm = method.getAnnotation(CustomAnnotation.class);
            System.out.println("方法上的注解值 === "+tm.value());
            // 获取方法上的所有参数注解  循环所有注解找到MyAnTargetParameter注解
            Annotation[][] annotations = method.getParameterAnnotations();
            for(Annotation[] tt : annotations){
                for(Annotation t1:tt){
                    if(t1 instanceof CustomAnnotation){
                        System.out.println("参数上的注解值 === "+((CustomAnnotation) t1).value());
                    }
                }
            }
            method.invoke(new AnnotationDemo(), "改变默认参数");
            // 获取AnnotationTest类上字段field的注解MyAnTargetField
            CustomAnnotation fieldAn = AnnotationDemo.class.getDeclaredField("field").getAnnotation(CustomAnnotation.class);
            System.out.println("字段上的注解值 === "+fieldAn.value());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
