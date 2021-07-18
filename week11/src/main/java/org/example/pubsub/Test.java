/**
 * <b>工程名：</b>week10<br/>
 * <b>包  名：</b>org.example.pubsub<br/>
 * <b>文件名：</b>Test.java<br/>
 * <b>日  期：</b>2021/07/18<br/>
 */
package org.example.pubsub;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * <b>类  名：</b>Test<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>qchang<br/>
 * <b>创建时间：</b>2021/07/18<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */

public class Test {

    public static void main(String[] args) throws InterruptedException {
        JedisPool jedisPool = getJedisPool();
        PublishOrder publishOrder = new PublishOrder(jedisPool);
        SubscribeOrder subscribeOrder = new SubscribeOrder(jedisPool);
        String channel = "C1";
        String orderId = "oooooo";
        subscribeOrder.sub(channel);
        Thread.sleep(10000);
        publishOrder.pub(channel,orderId);
    }

    private static JedisPool getJedisPool(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxWaitMillis(3000);
        poolConfig.setMaxTotal(10);
        poolConfig.setMaxIdle(5);
        return new JedisPool(poolConfig, "127.0.0.1", 6379, 10, "123456");
    }
}
