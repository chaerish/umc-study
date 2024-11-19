package com.umc.study.repository.member;

import com.umc.study.domain.Mission;
import java.util.List;

public interface MemberRepositoryCustom {
    List<Mission> dynamicQueryWithBooleanBuilder(String missionName);
}
