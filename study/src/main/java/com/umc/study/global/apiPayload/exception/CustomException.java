package com.umc.study.global.apiPayload.exception;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final BaseErrorCode code;

    public CustomException(BaseErrorCode code) {
        this.code = code;
    }

}
