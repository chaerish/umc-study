package com.umc.study.service.MemberService;

import com.umc.study.domain.Member;
import com.umc.study.domain.Mission;
import com.umc.study.domain.enums.MissionStatus;
import com.umc.study.domain.mapping.MemberMission;
import com.umc.study.repository.member.MemberRepository;
import com.umc.study.repository.mission.MemberMissionRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Optional<Member> findMember(Long id) {
        return memberRepository.findById(id);
    }

    public Page<Mission> getMyChallengeMission(Integer page) {
        Member member = findMember(1L).get();
        Page<MemberMission> memberMissions = memberMissionRepository.findAllByMemberAndStatus(member,
                MissionStatus.CHALLENGING,
                PageRequest.of(page, 10));
        return memberMissions.map(MemberMission::getMission);
    }
}
