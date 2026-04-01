package com.nova.coffee.infrastructure.tenant;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web 层租户配置。
 */
@Configuration
public class WebTenantConfig implements WebMvcConfigurer {

    private final TenantHeaderInterceptor tenantHeaderInterceptor;

    public WebTenantConfig(TenantHeaderInterceptor tenantHeaderInterceptor) {
        this.tenantHeaderInterceptor = tenantHeaderInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tenantHeaderInterceptor).addPathPatterns("/**");
    }
}
