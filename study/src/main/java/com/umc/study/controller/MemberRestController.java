package com.umc.study.controller;

import com.umc.study.converter.MemberConverter;
import com.umc.study.converter.StoreConverter;
import com.umc.study.domain.Member;
import com.umc.study.domain.mapping.MemberMission;
import com.umc.study.dto.MemberRequestDTO;
import com.umc.study.global.apiPayload.ApiResponse;
import com.umc.study.service.MemberService.MemberCommandService;
import com.umc.study.service.MemberService.MemberQueryService;
import com.umc.study.service.MissionService.MissionQueryServiceImpl;
import com.umc.study.validation.annotation.CheckPage;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;
    private final MissionQueryServiceImpl missionQueryService;

    @PostMapping("/")
    public ApiResponse<?> join(@RequestBody @Valid MemberRequestDTO.JoinDto request) {
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @PostMapping("/mission/{missionId}")
    @Operation(summary = "미션을 도전하기")
    public ApiResponse<?> addMissions(
            @PathVariable("missionId") Long missionId) {
        memberCommandService.challengeToMission(missionId);
        return ApiResponse.onSuccess(HttpStatus.OK);
    }

    @PostMapping("/mission/{missionId}")
    @Operation(summary = "내가 진행중인 미션 목록 보기")
    public ApiResponse<?> getMyChallengeMission(@CheckPage @RequestParam(name = "page") Integer page) {
        return ApiResponse.onSuccess(
                StoreConverter.missionPreViewListDTO(memberQueryService.getMyChallengeMission(page)));
    }

    @PutMapping("/mission/{missionId}")
    @Operation(summary = "미션 완료로 변환하기")
    public ApiResponse<?> updateMyMissionCompleted(@PathVariable("missionId") Long missionId) {
        MemberMission memberMission = missionQueryService.findMyMission(missionId);
        memberCommandService.completedMission(memberMission);
        return ApiResponse.onSuccess(HttpStatus.OK);
    }


}
