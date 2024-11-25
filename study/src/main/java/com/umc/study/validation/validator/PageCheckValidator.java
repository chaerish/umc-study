package com.umc.study.validation.validator;

import com.umc.study.domain.Store;
import com.umc.study.global.apiPayload.code.status.ErrorStatus;
import com.umc.study.service.StoreService.StoreQueryService;
import com.umc.study.validation.annotation.CheckPage;
import com.umc.study.validation.annotation.ExistStore;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PageCheckValidator implements ConstraintValidator<CheckPage,Long> {
    @Override
    public void initialize(CheckPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value != null && value > 0;
    }
}
