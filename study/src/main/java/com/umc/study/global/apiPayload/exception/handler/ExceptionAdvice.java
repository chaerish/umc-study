package com.umc.study.global.apiPayload.exception.handler;

import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import com.example.umc7th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc7th.global.apiPayload.exception.CustomException;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(CustomException.class)
    public CustomResponse<?> customException(CustomException e) {
        BaseErrorCode code = e.getCode();
        log.error(Arrays.toString(e.getStackTrace()));
        return CustomResponse.fail(code);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CustomResponse<?> customException(Exception e) {
        log.error(Arrays.toString(e.getStackTrace()));
        return CustomResponse.fail(GeneralErrorCode.INTERNAL_SERVER_ERROR_500);
    }

}
