package com.umc.study.controller;
import com.umc.study.dto.MissionRequestDTO;
import com.umc.study.dto.StoreReviewRequestDTO;
import com.umc.study.global.apiPayload.ApiResponse;
import com.umc.study.service.StoreService.StoreCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="store관련 api")
@RequestMapping("/stores")
public class StoreController {

    private final StoreCommandService storeCommandService;

    @PostMapping("/{storeId}/regions/{regionId}")
    @Operation(summary = "특정 지역에 가게 추가하기")
    public ApiResponse<?> addStoreToRegion(
            @PathVariable("storeId") Long storeId,
            @PathVariable("regionId") Long regionId) {
       storeCommandService.addStoreToRegion(storeId,regionId);
       return ApiResponse.onSuccess(HttpStatus.OK);
    }
    @PostMapping("/{storeId}/reviews")
    @Operation(summary = "특정 지역에 가게 추가하기")
    public ApiResponse<?> addReview(
            @PathVariable("storeId") Long storeId,
            @RequestBody StoreReviewRequestDTO dto) {
        storeCommandService.addReview(storeId,dto);
        return ApiResponse.onSuccess(HttpStatus.OK);
    }
    @PostMapping("/{storeId}/missions")
    @Operation(summary = "가게에 미션 추가하기")
    public ApiResponse<?> addMissions(
            @PathVariable("storeId") Long storeId,
            @RequestBody MissionRequestDTO dto
            ){
        storeCommandService.addMission(storeId,dto);
        return ApiResponse.onSuccess(HttpStatus.OK);
    }

}
