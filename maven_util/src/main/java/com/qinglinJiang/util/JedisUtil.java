package com.qinglinJiang.util;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JedisUtil {
    private static JedisPool jedisPool;

    static {
        try {
            Properties pro = new Properties();
            InputStream is = JedisUtil.class.getClassLoader().getResourceAsStream("jedis.properties");
            pro.load(is);
            int maxTotal = Integer.parseInt(pro.getProperty("maxTotal"));
            int maxIdle = Integer.parseInt(pro.getProperty("maxIdle"));
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(maxTotal);
            config.setMaxIdle(maxIdle);
            int port = Integer.parseInt(pro.getProperty("port"));
            String host = pro.getProperty("host");
            jedisPool = new JedisPool(config, host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

}
