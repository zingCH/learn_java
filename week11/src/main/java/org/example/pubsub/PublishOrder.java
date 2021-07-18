/**
 * <b>工程名：</b>week10<br/>
 * <b>包  名：</b>org.example<br/>
 * <b>文件名：</b>PublishOrder.java<br/>
 * <b>日  期：</b>2021/07/18<br/>
 */
package org.example.pubsub;


import lombok.extern.java.Log;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 发布
 */

@Log
public class PublishOrder {

    private JedisPool jedisPool;

    public PublishOrder(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public void pub(String channelName,String orderId){
        log.info("Start publisher order");
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.publish(channelName, orderId);
        }
    }
}
