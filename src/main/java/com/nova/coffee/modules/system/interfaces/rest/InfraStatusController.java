package com.nova.coffee.modules.system.interfaces.rest;

import com.nova.coffee.common.result.Result;
import com.nova.coffee.modules.system.application.InfraStatusApplicationService;
import com.nova.coffee.modules.system.interfaces.dto.InfraStatusResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 基础设施状态接口。
 */
@RestController
@RequestMapping("/api/system/infra")
public class InfraStatusController {

    private final InfraStatusApplicationService infraStatusApplicationService;

    public InfraStatusController(InfraStatusApplicationService infraStatusApplicationService) {
        this.infraStatusApplicationService = infraStatusApplicationService;
    }

    @GetMapping("/status")
    @PreAuthorize("hasRole('ADMIN')")
    public Result<InfraStatusResponse> status() {
        return Result.success(infraStatusApplicationService.getInfraStatus());
    }
}
