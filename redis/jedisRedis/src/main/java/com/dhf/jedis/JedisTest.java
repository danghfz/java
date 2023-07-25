package com.dhf.jedis;


import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author 党
 * @version 1.0
 * 2022/7/2   20:24
 */
public class JedisTest {
    static Jedis jedis;
    static {
        String host = "127.0.0.1";
        int port = 6379;
        jedis = new Jedis(host,port);
    }
    public static void main(String[] args) {
        //创建Jedis对象
        //public Jedis(String host, int port)
//        String host = "192.168.0.103";
        String host = "127.0.0.1";
        int port = 6379;
        Jedis jedis = new Jedis(host,port);

        //测试
        String ping = jedis.ping();//PONG
        //如果可以连接上,ping()会返回一个值
        System.out.println(ping);

    }
    //操作key
    @Test
    public void test1(){
        jedis.select(15);//切换数据库

        //设置多个key
        Set<String> keys = jedis.keys("*");//获取所以key
        for (String key: keys) {
            System.out.println(key);
        }
    }
}
