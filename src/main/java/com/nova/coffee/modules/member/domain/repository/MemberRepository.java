package com.nova.coffee.modules.member.domain.repository;

import com.nova.coffee.modules.member.domain.model.MemberProfile;
import java.util.Optional;

/**
 * 会员仓储接口。
 */
public interface MemberRepository {

    Optional<MemberProfile> findCurrentMember();
}
