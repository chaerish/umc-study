package com.umc.study.service.MemberService;


import com.umc.study.domain.Member;
import com.umc.study.domain.Mission;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface MemberQueryService {

    Optional<Member> findMember(Long id);

    Page<Mission> getMyChallengeMission(Integer page);
}
