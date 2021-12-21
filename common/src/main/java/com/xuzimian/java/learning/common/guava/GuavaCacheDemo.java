package com.xuzimian.globaldemo.common.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class GuavaCacheDemo {


    final static Cache<String, Object> cache = CacheBuilder.newBuilder()
            .maximumSize(5)
            .expireAfterWrite(1, TimeUnit.SECONDS)
            .build();

    @BeforeEach()
    public void putDataToCache() {
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        cache.put("key4", "value4");
        System.out.println("putDataToCache--执行了");
    }


    @Test
    public void getDatafromCache() throws InterruptedException {
        System.out.println(cache.getIfPresent("key1"));
        System.out.println(cache.getIfPresent("key2"));
        System.out.println(cache.getIfPresent("key3"));
    }

    @Test
    public void getDatafromCacheByLoad() throws ExecutionException, InterruptedException {
        Thread.sleep(1000 * 1);
        System.out.println(cache.get("key1", new Callable() {
            @Override
            public Object call() throws Exception {
                return "--我是自动拿到的数据--";
            }
        }));

    }
}
