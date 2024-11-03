package com.umc.study.repository.mission;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc.study.domain.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission,Long> {
}
