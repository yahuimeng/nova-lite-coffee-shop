package com.nova.coffee.modules.system.interfaces.dto;

/**
 * 基础设施连通性响应。
 */
public record InfraStatusResponse(
    String mysqlStatus,
    String redisStatus,
    boolean redisLockReady,
    long productCount,
    String cacheValue
) {
}
