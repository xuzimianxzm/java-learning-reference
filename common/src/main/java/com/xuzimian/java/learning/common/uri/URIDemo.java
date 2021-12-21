package com.xuzimian.globaldemo.common.uri;

import org.junit.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * @program: global-demo
 * @description:
 * @author: xzm
 * @create: 2019-04-17 15:47
 **/
public class URIDemo {


    /**
     * 通过自动装配为spring bean的Resouce属性自动装配资源文件
     * @throws IOException
     */
    @Test
    public void testSpringAutowiredResouce() throws IOException {
        ApplicationContext context=new ClassPathXmlApplicationContext("spring-resouce.xml");
        ResouceBean resouceBean= (ResouceBean) context.getBean("resouceBean");
        System.out.println(resouceBean.getTempResource().getURI());
    }


    @Test
    public void testSpringUrlResouce() throws MalformedURLException {
        UrlResource urlResource = new UrlResource("file:web.xml");
        System.out.println(urlResource.exists());

    }

    /**
     * 主要优势是方便访问类加载路径下的资源，尤其是Web应用，因为它可以自动搜索位于WEB-INF/classes下的资源文件
     * @throws MalformedURLException
     */
    @Test
    public void testSpringClassPathResource() throws MalformedURLException {
        Resource resource = new ClassPathResource("book.xml");
        System.out.println(resource.exists());


    }
}
