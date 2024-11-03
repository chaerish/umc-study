package com.umc.study.repository.mission;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc.study.domain.Member;
import com.umc.study.domain.Mission;
import com.umc.study.domain.QMember;
import com.umc.study.domain.QMission;
import com.umc.study.domain.QStore;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QMissionRepository implements MissionRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    private final QMission mission;
    private final QStore store;
    private final QMember member;
    @Override
    public Page<Mission> dynamicQueryWithBooleanBuilder(Member member, Pageable pageable) {
        List<Mission> missions = queryFactory
                .selectFrom(mission)
                .join(mission.store)
                .where(mission.member.eq(member))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        long total = queryFactory
                .selectFrom(mission)
                .where(mission.member.eq(member))
                .fetchCount();

        return new PageImpl<>(missions,pageable,total);
    }
    public Page<Mission> findMissionsByMemberAddress(Long memberId, Pageable pageable){
        List<Mission> missions = queryFactory
                .selectFrom(mission)
                .join(mission.store,store)
                .join(member).on(member.userAddress.eq(store.storeAddress))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .where(member.userId.eq(memberId))
                .fetch();
        long total =  queryFactory
                .selectFrom(mission)
                .join(mission.store,store)
                .join(member).on(member.userAddress.eq(store.storeAddress))
                .where(member.userId.eq(memberId))
                .fetch().size();
        return new PageImpl<>(missions,pageable,total);

    }
}
