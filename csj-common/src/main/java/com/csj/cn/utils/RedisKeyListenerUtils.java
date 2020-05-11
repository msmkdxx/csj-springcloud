package com.csj.cn.utils;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author chensijia
 * @Date 2020/4/1017:12
 */
@Component
public class RedisKeyListenerUtils extends KeyExpirationEventMessageListener {
    public RedisKeyListenerUtils(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        super.onMessage(message, pattern);
    }
}
