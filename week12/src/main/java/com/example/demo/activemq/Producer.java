package com.example.demo.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;

import javax.jms.Queue;
import javax.jms.Topic;


public class Producer {

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    @Autowired
    private JmsMessagingTemplate jms;

    /**
     * 点对点模式（queue）模式发消息
     *
     * @param text
     */
    public String queueSend(String text) {
        //发送消息至消息中间件代理（Broker）
        jms.convertAndSend(queue, text);
        return "success";
    }

    /**
     * 订阅模式（topic）发送消息
     *
     * @param text
     * @return
     */
    public String topicSend(String text) {
        jms.convertAndSend(topic, text);
        return "topic 发送成功";
    }
}
