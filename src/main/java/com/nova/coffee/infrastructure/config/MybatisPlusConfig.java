package com.nova.coffee.infrastructure.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis-Plus 基础配置。
 *
 * <p>当前先扫描基础 Mapper，后续接入多模块数据库表时可以继续按模块扩展。
 */
@Configuration
@MapperScan("com.nova.coffee.modules")
public class MybatisPlusConfig {
}
