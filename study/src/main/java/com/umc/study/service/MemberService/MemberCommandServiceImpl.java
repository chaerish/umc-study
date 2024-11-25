package com.umc.study.service.MemberService;

import com.umc.study.converter.MemberConverter;
import com.umc.study.converter.MemberPreferConverter;
import com.umc.study.domain.FoodCategory;
import com.umc.study.domain.Member;
import com.umc.study.domain.Mission;
import com.umc.study.domain.mapping.MemberMission;
import com.umc.study.domain.mapping.MemberPrefer;
import com.umc.study.dto.MemberRequestDTO;
import com.umc.study.global.apiPayload.code.status.ErrorStatus;
import com.umc.study.global.apiPayload.exception.handler.FoodCategoryHandler;
import com.umc.study.repository.category.FoodCategoryRepository;
import com.umc.study.repository.member.MemberRepository;
import com.umc.study.repository.mission.MemberMissionRepository;
import com.umc.study.repository.mission.MissionRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.ErrorResponseException;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {

        Member newMember = MemberConverter.toMember(request);
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> foodCategoryRepository.findById(category)
                        .orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND)))
                .collect(Collectors.toList());

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> {
            memberPrefer.setMember(newMember);
        });

        return memberRepository.save(newMember);
    }

    @Override
    public void challengeToMission(Long missionId) {
        Mission mission = missionRepository.findById(missionId).orElseThrow(
                () -> new FoodCategoryHandler(ErrorStatus.MISSION_NOT_FOUND)
        );
        Member member = memberRepository.findById(1L).orElseThrow(
                () -> new ErrorResponseException(ErrorStatus.MEMBER_NOT_FOUND.getHttpStatus())
        );
        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .build();
        memberMissionRepository.save(memberMission);
    }

    @Override
    @Transactional
    public void completedMission(MemberMission memberMission) {
        memberMission.completed();
    }
}