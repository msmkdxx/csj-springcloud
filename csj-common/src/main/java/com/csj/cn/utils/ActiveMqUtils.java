package com.csj.cn.utils;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

/**
 * @Description TODO
 * @Author chensijia
 * @Date 2020/4/915:25
 */
@Component
public class ActiveMqUtils {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendQueueMsg(String name, Object msg) {
        Destination destination = new ActiveMQQueue(name);
        jmsMessagingTemplate.convertAndSend(destination, msg);
    }

    public void sendTopicMsg(String name, Object msg) {
        Destination destination = new ActiveMQTopic(name);
        jmsMessagingTemplate.convertAndSend(destination, msg);
    }
}
