package com.xuzimian.globaldemo.common.redis;

import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class SimpleJedisDemo {

    @Test
    public void testConect() {
        //连接本地的 Redis 服务
        Jedis jedis = getJedis();
        //查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());
    }

    @Test
    public void testUse() {
        //连接本地的 Redis 服务
        Jedis jedis = getJedis();

        System.out.println(jedis.hmget("20180815-Ag(T+D)", "jsj"));
        System.out.println(jedis.hmget("20180815-Ag(T+D)", "no key"));
        System.out.println(jedis.hmget("20180815-Ag(T+D)", "no key1", "no key2"));
    }

    @Test
    public void testDelete() throws ParseException {
        //连接本地的 Redis 服务
        Jedis jedis = getJedis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        String initDateStr = "20190426";
        Date init_date = sdf.parse(initDateStr);

        String keyTemlate = "autd:jsj:{0}-{1}";
        String[] codes = {"Ag(T+D)", "Au(T+D)", "Au(T+N1)", "Au(T+N2)", "Au100g", "Au99.95", "Au99.99", "mAu(T+D)"};
        for (int n = 0; n <= 10; n++) {
            initDateStr = sdf.format(new Date(init_date.getTime() + (long) n * 24 * 60 * 60 * 1000));
            for (String code : codes) {
                String key = MessageFormat.format(keyTemlate, initDateStr, code);
                System.out.println(key + ":" + jedis.del(key));
            }
        }
    }

    @Test
    public void testWrite() throws ParseException {
        Jedis jedis = getJedis();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        String initDateStr = "20190507";
        Date init_date = sdf.parse(initDateStr);

        String keyTemlate = "autd:jsj:{0}-{1}";
        String[] codes = {"Ag(T+D)", "Au(T+D)", "Au(T+N1)", "Au(T+N2)", "Au100g", "Au99.95", "Au99.99", "mAu(T+D)"};

        for (String code : codes) {
            String key = MessageFormat.format(keyTemlate, initDateStr, code);
            System.out.println(key + " : " + jedis.hset(key, "isnew", "1") + " -- " + jedis.hget(key, "isnew"));
        }
    }


    @Test
    public void testhmgets() throws ParseException {
        Jedis jedis = getJedis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        String keyTemlate = "autd:niuren:{0}";

        jedis.hset(MessageFormat.format(keyTemlate, "1000000001"), "1000000002", "2019-05-01");
        jedis.hset(MessageFormat.format(keyTemlate, "1000000001"), "1000000004", "2019-05-20");
        jedis.hset(MessageFormat.format(keyTemlate, "1000000001"), "100000000a", "2019-05-26");
        jedis.hset(MessageFormat.format(keyTemlate, "1000000001"), "1000000002", new Date().toString());


        Optional.ofNullable(jedis.hgetAll(MessageFormat.format(keyTemlate, "1000000001")))
                .orElseGet(() -> Maps.newHashMap())
                .forEach((k, v) -> System.out.println(k + " : " + v));

    }

    /**
     * 连接本地的 Redis 服务
     *
     * @return
     */
    private Jedis getJedis() {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("47.98.36.186", 6379);
        jedis.auth("Y9m7UDxrPj0COF3");
        System.out.println("连接成功");
        return jedis;
    }
}
