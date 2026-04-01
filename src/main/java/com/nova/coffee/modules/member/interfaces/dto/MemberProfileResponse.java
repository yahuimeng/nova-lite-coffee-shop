package com.nova.coffee.modules.member.interfaces.dto;

/**
 * 会员信息响应。
 */
public record MemberProfileResponse(
    Long memberId,
    String nickname,
    String level,
    int points
) {
}
