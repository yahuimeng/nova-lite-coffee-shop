package com.nova.coffee.modules.member.domain.model;

/**
 * 会员档案。
 */
public record MemberProfile(
    Long memberId,
    String nickname,
    String level,
    int points
) {
}
