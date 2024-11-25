package com.umc.study.service.MissionService;

import com.umc.study.domain.Mission;
import com.umc.study.domain.mapping.MemberMission;
import com.umc.study.global.apiPayload.code.status.ErrorStatus;
import com.umc.study.global.apiPayload.exception.handler.FoodCategoryHandler;
import com.umc.study.repository.mission.MemberMissionRepository;
import com.umc.study.repository.mission.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    public Mission findByMission(Long missionId) {
        return missionRepository.findById(missionId).orElseThrow(
                () -> new FoodCategoryHandler(ErrorStatus.MISSION_NOT_FOUND)
        );
    }

    public MemberMission findMyMission(Long missionId) {
        return memberMissionRepository.findById(missionId).orElseThrow(
                () -> new FoodCategoryHandler(ErrorStatus.MISSION_NOT_FOUND)
        );
    }


}
