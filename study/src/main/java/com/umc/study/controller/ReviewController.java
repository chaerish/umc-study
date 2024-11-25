package com.umc.study.controller;
import com.umc.study.converter.ReviewConverter;
import com.umc.study.converter.StoreConverter;
import com.umc.study.dto.MissionRequestDTO;
import com.umc.study.dto.StoreReviewRequestDTO;
import com.umc.study.global.apiPayload.ApiResponse;
import com.umc.study.service.StoreService.StoreCommandService;
import com.umc.study.service.StoreService.StoreQueryService;
import com.umc.study.service.review.ReviewQueryService;
import com.umc.study.validation.annotation.CheckPage;
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
@Tag(name="review관련 api")
@RequestMapping("/reviewss")
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "내 리뷰 목록 조회 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<?> getMyReviewsList(@PathVariable Long storeId,
                                        @CheckPage @RequestParam(name = "page") Integer page){
        return ApiResponse.onSuccess(ReviewConverter.reviewPreviewListDTO(reviewQueryService.getMyReview(page,storeId)));
    }



}
