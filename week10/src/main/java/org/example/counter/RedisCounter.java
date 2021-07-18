/**
 * <b>工程名：</b>week10<br/>
 * <b>包  名：</b>org.example.counter<br/>
 * <b>文件名：</b>RedisCounter.java<br/>
 * <b>日  期：</b>2021/07/18<br/>
 */
package org.example.counter;

import com.google.common.collect.Lists;
import lombok.extern.java.Log;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 分布式计数器，模拟减库存
 */

@Log
public class RedisCounter {

    private final static String STOCK_KEY_PREFIX = "STOCK_KEY";

    public static boolean count(JedisPool jedisPool,String lockKey,long num){
        Jedis jedis = jedisPool.getResource();
        String key = STOCK_KEY_PREFIX+lockKey;
        if(Long.valueOf(jedis.get(key))>num){
            String script = "return redis.call('incrby', KEYS[1], ARGV[1])";
            Object eval = jedis.eval(script, Lists.newArrayList(STOCK_KEY_PREFIX+lockKey), Lists.newArrayList(String.valueOf(0-num)));
            if(null != eval &&  Integer.valueOf(String.valueOf(eval)) >= 0){
                log.info(lockKey+" 扣减库存成功,库存剩余:"+String.valueOf(eval));
                return true;
            }
            log.info("库存不足");
        }
        log.info("扣减库存失败");
        return false;
    }
}
