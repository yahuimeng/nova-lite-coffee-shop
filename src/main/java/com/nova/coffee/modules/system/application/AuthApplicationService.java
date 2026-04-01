package com.nova.coffee.modules.system.application;

import com.nova.coffee.infrastructure.security.JwtTokenService;
import com.nova.coffee.modules.system.interfaces.dto.LoginTokenResponse;
import org.springframework.stereotype.Service;

/**
 * 认证应用服务。
 *
 * <p>当前先做学习版，使用固定演示用户。
 */
@Service
public class AuthApplicationService {

    private final JwtTokenService jwtTokenService;

    public AuthApplicationService(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    public LoginTokenResponse generateAdminToken() {
        return new LoginTokenResponse(
            "demo-admin",
            "ADMIN",
            jwtTokenService.generateToken("demo-admin", "ADMIN")
        );
    }
}
