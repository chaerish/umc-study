package com.umc.study.converter;

import com.umc.study.domain.Mission;
import com.umc.study.domain.Review;
import com.umc.study.dto.store.StoreResponseDTO;
import com.umc.study.dto.store.StoreResponseDTO.MissionPreViewDTO;
import com.umc.study.dto.store.StoreResponseDTO.ReviewPreViewDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;

public class StoreConverter {
    public static StoreResponseDTO.ReviewPreViewDTO reviewPreviewDTO(Review review) {
        return StoreResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }

    public static StoreResponseDTO.ReviewPreViewListDTO reviewPreviewListDTO(Page<Review> reviewList) {
        List<ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(StoreConverter::reviewPreviewDTO).collect(Collectors.toList());

        return StoreResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

    public static StoreResponseDTO.MissionPreViewDTO missionPreViewDTO(Mission mission) {
        return StoreResponseDTO.MissionPreViewDTO
                .builder()
                .missionSpec(mission.getMissionSpec())
                .storeName(mission.getStore().getName())
                .deadline(mission.getDeadline())
                .reward(mission.getReward())
                .build();
    }

    public static StoreResponseDTO.MissionPreViewListDTO missionPreViewListDTO(Page<Mission> mission) {
        List<MissionPreViewDTO> missionList = mission.getContent().stream()
                .map(StoreConverter::missionPreViewDTO)
                .toList();
        return StoreResponseDTO.MissionPreViewListDTO
                .builder()
                .isLast(mission.isLast())
                .isFirst(mission.isFirst())
                .totalPage(mission.getTotalPages())
                .totalElements(mission.getTotalElements())
                .listSize(missionList.size())
                .missionList(missionList)
                .build();
    }

}
