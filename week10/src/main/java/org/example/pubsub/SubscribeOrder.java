/**
 * <b>工程名：</b>week10<br/>
 * <b>包  名：</b>org.example<br/>
 * <b>文件名：</b>SubscribeOrder.java<br/>
 * <b>日  期：</b>2021/07/18<br/>
 */
package org.example.pubsub;

import lombok.extern.java.Log;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

/**
 * 订阅
 */

@Log
public class SubscribeOrder {

    private JedisPool jedisPool;
    public SubscribeOrder(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public void sub(String channelName){
        log.info("sub order");
        new Thread(()->{
            try(Jedis jedis = jedisPool.getResource()) {
                jedis.subscribe(setupSubscriber(), channelName);
            }
        }).start();
    }

    private JedisPubSub setupSubscriber() {
        return new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                if (message.isEmpty()) {
                    log.info("SubPub End");
                }
                log.info("Receive message from "+channel+" orderId : "+ message);
            }
        };
    }
}
