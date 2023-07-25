package com.dhf.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author 党
 * @version 1.0
 * 2022/7/4   15:16
 */
public class JedisUtils {
    private static JedisPool jedisPool = null;
    public static JedisPool getJedisPool(String host,Integer port){
        if (jedisPool==null){
            synchronized (JedisPool.class){
                JedisPoolConfig redisConfig = new JedisPoolConfig();
                redisConfig.setMaxTotal(200);
                redisConfig.setMaxIdle(32);
                redisConfig.setMaxWaitMillis(100*1000);
                redisConfig.setBlockWhenExhausted(true);
                redisConfig.setTestOnBorrow(true);
                jedisPool = new JedisPool(redisConfig,host,port);
            }
        }
        return jedisPool;
    }
}
