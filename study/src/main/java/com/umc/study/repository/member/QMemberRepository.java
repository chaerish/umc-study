package com.umc.study.repository.member;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc.study.domain.Member;
import com.umc.study.domain.QMember;
import com.umc.study.domain.QReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QMemberRepository {
    private final JPAQueryFactory queryFactory;
    private final QMember member;
    public Member getProfile(Long memberId){
        return queryFactory.selectFrom(member)
                .where(member.userId.eq(memberId))
                .fetchOne();
    }


}
