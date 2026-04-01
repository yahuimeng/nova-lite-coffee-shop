package com.nova.coffee.modules.member.infrastructure.persistence;

import com.nova.coffee.modules.member.domain.model.MemberProfile;
import com.nova.coffee.modules.member.domain.repository.MemberRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 * 内存版会员仓储。
 */
@Repository
public class InMemoryMemberRepository implements MemberRepository {

    @Override
    public Optional<MemberProfile> findCurrentMember() {
        return Optional.of(new MemberProfile(1001L, "演示用户", "GOLD", 128));
    }
}
