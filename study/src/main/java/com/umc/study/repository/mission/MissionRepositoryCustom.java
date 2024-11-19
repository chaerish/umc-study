package com.umc.study.repository.mission;

import com.umc.study.domain.Member;
import com.umc.study.domain.Mission;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MissionRepositoryCustom {
    Page<Mission> dynamicQueryWithBooleanBuilder(Member member, Pageable pageable);
}
