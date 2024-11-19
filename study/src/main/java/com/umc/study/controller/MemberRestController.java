package com.umc.study.controller;

import com.umc.study.converter.MemberConverter;
import com.umc.study.domain.Member;
import com.umc.study.dto.MemberRequestDTO;
import com.umc.study.global.apiPayload.ApiResponse;
import com.umc.study.service.MemberService.MemberCommandService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;

    @PostMapping("/")
    public ApiResponse<?> join(@RequestBody @Valid MemberRequestDTO.JoinDto request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }
    @PostMapping("/mission/{missionId}")
    @Operation(summary = "미션을 도전하기")
    public ApiResponse<?> addMissions(
            @PathVariable("missionId") Long missionId){
        memberCommandService.challengeToMission(missionId);
        return ApiResponse.onSuccess(HttpStatus.OK);
    }
}
