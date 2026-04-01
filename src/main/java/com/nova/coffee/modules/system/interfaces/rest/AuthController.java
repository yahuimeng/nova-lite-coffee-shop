package com.nova.coffee.modules.system.interfaces.rest;

import com.nova.coffee.common.result.Result;
import com.nova.coffee.modules.system.application.AuthApplicationService;
import com.nova.coffee.modules.system.interfaces.dto.LoginTokenResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证接口。
 */
@RestController
@RequestMapping("/api/system/auth")
public class AuthController {

    private final AuthApplicationService authApplicationService;

    public AuthController(AuthApplicationService authApplicationService) {
        this.authApplicationService = authApplicationService;
    }

    /**
     * 学习版直接返回一个管理员令牌，方便快速体验完整请求链路。
     */
    @PostMapping("/token")
    public Result<LoginTokenResponse> generateToken() {
        return Result.success(authApplicationService.generateAdminToken());
    }
}
