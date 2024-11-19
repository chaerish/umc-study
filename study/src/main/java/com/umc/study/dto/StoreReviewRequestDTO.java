package com.umc.study.dto;

public record StoreReviewRequestDTO(
        Float score,
        String title,
        String body
) {
}
