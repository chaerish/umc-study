package com.umc.study.converter;

import com.umc.study.domain.Review;
import com.umc.study.dto.store.StoreResponseDTO;
import com.umc.study.dto.store.StoreResponseDTO.ReviewPreViewDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;

public class ReviewConverter {
    public static ReviewPreViewDTO reviewPreviewDTO(Review review){
        return ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }
    public static StoreResponseDTO.ReviewPreViewListDTO reviewPreviewListDTO(Page<Review> reviewList){
        List<ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(ReviewConverter::reviewPreviewDTO).collect(Collectors.toList());

        return StoreResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }
}
