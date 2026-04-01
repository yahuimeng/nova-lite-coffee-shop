package com.nova.coffee.infrastructure.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 使用日志模拟 MQ 发送。
 */
@Component
public class LogMqProducer implements MqProducer {

    private static final Logger log = LoggerFactory.getLogger(LogMqProducer.class);

    @Override
    public void send(String topic, Object payload) {
        log.info("模拟发送 MQ 消息, topic={}, payload={}", topic, payload);
    }
}
