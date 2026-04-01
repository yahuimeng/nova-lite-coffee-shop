package com.nova.coffee.modules.system.interfaces.dto;

/**
 * 登录响应。
 */
public record LoginTokenResponse(
    String username,
    String role,
    String token
) {
}
