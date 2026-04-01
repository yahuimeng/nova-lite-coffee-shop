package com.nova.coffee.infrastructure.mq;

/**
 * 统一 MQ 发送抽象。
 */
public interface MqProducer {

    void send(String topic, Object payload);
}
