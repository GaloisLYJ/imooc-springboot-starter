package com.imooc.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Jedis学习
 *
 * @author
 * @create 2019-02-10 22:16
 **/
public class JedisTest {
    @Test
    public void demo1(){
        //1、设置IP地址和端口,redis密码
        Jedis jedis = new Jedis("119.29.105.204",6379);
        jedis.auth("yujian");
        //2、保存数据
        jedis.set("name1","imooc1");
        //3、获取数据
        String value = jedis.get("name1");
        System.out.println(value);
        //释放资源
        jedis.close();
    }

    @Test
    /**
      连接池方式
     */
    public void demo2(){
        //获得连接池配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        //设置最大连接数
        config.setMaxTotal(30);
        //设置最大空闲连接数
        config.setMaxIdle(10);
        //获得连接池
        JedisPool jedisPool = new JedisPool(config,"119.29.105.204",6379);
        //获得核心对象
        Jedis jedis = null;
        try{
            //通过连接池来获得连接
            jedis = jedisPool.getResource();
            jedis.auth("yujian");
            //设置数据
            jedis.set("name2","imooc2");
            //获取数据
            String value = jedis.get("name2");
            System.out.println(value);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源
            if(jedis != null){
                jedis.close();
            }
            if(jedisPool != null){
                jedisPool.close();
            }
        }
    }
}
