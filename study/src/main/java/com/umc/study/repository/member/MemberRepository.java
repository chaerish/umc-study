package com.umc.study.repository.member;

import com.umc.study.domain.Member;
import com.umc.study.domain.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
