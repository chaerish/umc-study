package com.umc.study.global.apiPayload.exception.handler;

import com.umc.study.global.apiPayload.code.BaseErrorCode;
import com.umc.study.global.apiPayload.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
