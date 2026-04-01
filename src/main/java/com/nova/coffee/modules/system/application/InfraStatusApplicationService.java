package com.nova.coffee.modules.system.application;

import com.nova.coffee.modules.system.interfaces.dto.InfraStatusResponse;
import java.time.Duration;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 基础设施状态应用服务。
 *
 * <p>用于确认 MySQL、Redis、Redisson 是否已经连通。
 */
@Service
public class InfraStatusApplicationService {

    private final ProductPersistenceProbe productPersistenceProbe;
    private final StringRedisTemplate stringRedisTemplate;
    private final RedissonClient redissonClient;

    public InfraStatusApplicationService(
        ProductPersistenceProbe productPersistenceProbe,
        StringRedisTemplate stringRedisTemplate,
        RedissonClient redissonClient
    ) {
        this.productPersistenceProbe = productPersistenceProbe;
        this.stringRedisTemplate = stringRedisTemplate;
        this.redissonClient = redissonClient;
    }

    @Transactional(readOnly = true)
    public InfraStatusResponse getInfraStatus() {
        String mysqlStatus;
        long productCount = 0L;
        try {
            productCount = productPersistenceProbe.countProducts();
            mysqlStatus = "UP";
        } catch (DataAccessException exception) {
            mysqlStatus = "DOWN";
        }

        String redisStatus;
        String cacheValue = null;
        try {
            stringRedisTemplate.opsForValue().set("coffee:infra:ping", "pong", Duration.ofMinutes(5));
            cacheValue = stringRedisTemplate.opsForValue().get("coffee:infra:ping");
            redisStatus = "UP";
        } catch (Exception exception) {
            redisStatus = "DOWN";
        }

        boolean redisLockReady = false;
        try {
            RLock lock = redissonClient.getLock("coffee:infra:lock");
            redisLockReady = lock.tryLock();
            if (redisLockReady) {
                lock.unlock();
            }
        } catch (Exception exception) {
            redisLockReady = false;
        }

        return new InfraStatusResponse(mysqlStatus, redisStatus, redisLockReady, productCount, cacheValue);
    }
}
