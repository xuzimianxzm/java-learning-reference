package com.xuzimian.globaldemo.common.basic.annotation;


import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.FIELD,ElementType.CONSTRUCTOR,ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CustomAnnotation {
    String value() default "hello annotation";
}
