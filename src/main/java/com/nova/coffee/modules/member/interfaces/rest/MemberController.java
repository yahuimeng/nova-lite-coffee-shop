package com.nova.coffee.modules.member.interfaces.rest;

import com.nova.coffee.common.result.Result;
import com.nova.coffee.modules.member.application.MemberApplicationService;
import com.nova.coffee.modules.member.interfaces.dto.MemberProfileResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员接口。
 */
@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberApplicationService memberApplicationService;

    public MemberController(MemberApplicationService memberApplicationService) {
        this.memberApplicationService = memberApplicationService;
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('ADMIN')")
    public Result<MemberProfileResponse> currentMember() {
        return Result.success(memberApplicationService.getCurrentMemberProfile());
    }
}
