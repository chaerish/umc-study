package com.umc.study.controller;

import com.umc.study.converter.StoreConverter;
import com.umc.study.dto.MissionRequestDTO;
import com.umc.study.dto.StoreReviewRequestDTO;
import com.umc.study.global.apiPayload.ApiResponse;
import com.umc.study.service.StoreService.StoreCommandService;
import com.umc.study.service.StoreService.StoreQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@Tag(name = "store관련 api")
@RequestMapping("/stores")
public class StoreController {

    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;

    @PostMapping("/{storeId}/regions/{regionId}")
    @Operation(summary = "특정 지역에 가게 추가하기")
    public ApiResponse<?> addStoreToRegion(
            @PathVariable("storeId") Long storeId,
            @PathVariable("regionId") Long regionId) {
        storeCommandService.addStoreToRegion(storeId, regionId);
        return ApiResponse.onSuccess(HttpStatus.OK);
    }

    @PostMapping("/{storeId}/reviews")
    @Operation(summary = "특정 지역에 가게 추가하기")
    public ApiResponse<?> addReview(
            @PathVariable("storeId") Long storeId,
            @RequestBody StoreReviewRequestDTO dto) {
        storeCommandService.addReview(storeId, dto);
        return ApiResponse.onSuccess(HttpStatus.OK);
    }

    @PostMapping("/{storeId}/missions")
    @Operation(summary = "가게에 미션 추가하기")
    public ApiResponse<?> addMissions(
            @PathVariable("storeId") Long storeId,
            @RequestBody MissionRequestDTO dto
    ) {
        storeCommandService.addMission(storeId, dto);
        return ApiResponse.onSuccess(HttpStatus.OK);
    }

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<?> getReviewsList(@PathVariable Long storeId,
                                         @RequestParam(name = "page") Integer page) {
        return ApiResponse.onSuccess(
                StoreConverter.reviewPreviewListDTO(storeQueryService.getReviewList(storeId, page)));
    }

    @GetMapping("/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<?> getMissionsList(@PathVariable Long storeId,
                                          @RequestParam(name = "page") Integer page) {
        return ApiResponse.onSuccess(
                StoreConverter.missionPreViewListDTO(storeQueryService.getMissionList(storeId, page)));
    }


}
