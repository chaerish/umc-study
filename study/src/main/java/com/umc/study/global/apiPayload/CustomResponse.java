package com.umc.study.global.apiPayload;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import com.example.umc7th.global.apiPayload.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;

@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // null 값인 필드를 JSON 응답에 포함하지 않음
public class CustomResponse<T> {
    @JsonProperty("isSuccess")
    private boolean isSuccess;
    @JsonProperty("status")
    private HttpStatus httpStatus;
    @JsonProperty("code")
    private String code;
    @JsonProperty("message")
    private String message;
    @JsonProperty("data")
    private T data;

    /**
     * 성공 반환: 내려줄 데이터가 없을 때
     *
     * @return CustomResponse
     * @Param no param
     */
    public static CustomResponse<?> onSuccess(BaseSuccessCode baseSuccessCode) {
        return CustomResponse.builder()
                .isSuccess(true)
                .httpStatus(baseSuccessCode.getStatus())
                .code(String.valueOf(baseSuccessCode.getCode()))
                .message(baseSuccessCode.getMessage())
                .build();
    }

    /**
     * 성공, 내려줄 데이터가 있을 때.
     *
     * @param data
     * @param <T>
     * @return CustomResponse<T>
     */

    public static <T> CustomResponse<T> onSuccess(BaseSuccessCode baseSuccessCode, T data) {
        return CustomResponse.<T>builder()
                .isSuccess(true)
                .httpStatus(baseSuccessCode.getStatus())
                .code(String.valueOf(baseSuccessCode.getCode()))
                .message(baseSuccessCode.getMessage())
                .data(data)
                .build();

    }

    /**
     * 실패
     *
     * @param errorCode
     * @return CustomResponse
     */

    public static CustomResponse<?> fail(BaseErrorCode errorCode) {
        return CustomResponse.builder()
                .isSuccess(false)
                .httpStatus(errorCode.getStatus())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
    }

}
