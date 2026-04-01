package com.nova.coffee.modules.member.application;

import com.nova.coffee.common.exception.BizException;
import com.nova.coffee.modules.member.domain.repository.MemberRepository;
import com.nova.coffee.modules.member.interfaces.dto.MemberProfileResponse;
import org.springframework.stereotype.Service;

/**
 * 会员应用服务。
 */
@Service
public class MemberApplicationService {

    private final MemberRepository memberRepository;

    public MemberApplicationService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberProfileResponse getCurrentMemberProfile() {
        return memberRepository.findCurrentMember()
            .map(member -> new MemberProfileResponse(member.memberId(), member.nickname(), member.level(), member.points()))
            .orElseThrow(() -> new BizException("当前会员不存在"));
    }
}
