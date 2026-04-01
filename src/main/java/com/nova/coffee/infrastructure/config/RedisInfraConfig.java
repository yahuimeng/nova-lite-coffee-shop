package com.nova.coffee.infrastructure.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * Redis 与 Redisson 基础配置。
 */
@Configuration
public class RedisInfraConfig {

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient(
        @Value("${app.redisson.address}") String address,
        @Value("${app.redisson.password:}") String password,
        @Value("${app.redisson.database:0}") int database
    ) {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer()
            .setAddress(address)
            .setDatabase(database);

        if (StringUtils.hasText(password)) {
            singleServerConfig.setPassword(password);
        }
        return Redisson.create(config);
    }
}
