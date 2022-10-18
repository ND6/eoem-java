package com.eoem.cache;

import redis.clients.jedis.Jedis;

public class RedisJava {

      public static void main(String[] args) {

          //Connecting to Redis server onlocalhost

         Jedis jedis = new Jedis("127.0.0.1",6379);

         System.out.println("Connection to server successfully");

          //check whether server is running or not

         System.out.println("Server is running: "+jedis.ping());

         jedis.set("hell","hw");

         //设置key=hell的过期时间，单位是s。
         jedis.expire("hell", 20);
         String value = jedis.get("hell");

         System.out.println(value);

       }

}