package com.umc.study.service.StoreService;

import com.umc.study.domain.Member;
import com.umc.study.domain.Mission;
import com.umc.study.domain.Region;
import com.umc.study.domain.Review;
import com.umc.study.domain.Store;
import com.umc.study.domain.mapping.MemberMission;
import com.umc.study.dto.MissionRequestDTO;
import com.umc.study.dto.StoreReviewRequestDTO;
import com.umc.study.global.apiPayload.code.status.ErrorStatus;
import com.umc.study.repository.member.MemberRepository;
import com.umc.study.repository.mission.MemberMissionRepository;
import com.umc.study.repository.mission.MissionRepository;
import com.umc.study.repository.region.RegionRepository;
import com.umc.study.repository.review.ReviewRepository;
import com.umc.study.repository.store.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

@Service
@RequiredArgsConstructor
public class StoreCommandService {
    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private  final MissionRepository missionRepository;
    @Transactional
    public void addStoreToRegion(Long storeId, Long regionId){
        Region region = regionRepository.findById(regionId).orElseThrow(
                ()-> new ErrorResponseException(ErrorStatus.REGION_NOT_FOUND.getHttpStatus())
        );
        Store store = storeRepository.findById(storeId).orElseThrow(
                ()-> new  ErrorResponseException(ErrorStatus.STORE_NOT_FOUND.getHttpStatus())
        );
        store.addRegion(region);
    }
    @Transactional
    public void addReview(Long storeId,StoreReviewRequestDTO dto){
        Store store = storeRepository.findById(storeId).orElseThrow(
                ()-> new ErrorResponseException(ErrorStatus.STORE_NOT_FOUND.getHttpStatus())
        );
        Member member =memberRepository.findById(1L).orElseThrow(
                ()-> new ErrorResponseException(ErrorStatus.MEMBER_NOT_FOUND.getHttpStatus())
        );
        Review review = Review.builder()
                .title(dto.title())
                .member(member)
                .score(dto.score())
                .store(store)
                .body(dto.body())
                .build();
        reviewRepository.save(review);
    }
    @Transactional
    public void addMission(Long storeId, MissionRequestDTO dto){
        Store store = storeRepository.findById(storeId).orElseThrow(
                ()-> new ErrorResponseException(ErrorStatus.STORE_NOT_FOUND.getHttpStatus())
        );
        Member member =memberRepository.findById(1L).orElseThrow(
                ()-> new ErrorResponseException(ErrorStatus.MEMBER_NOT_FOUND.getHttpStatus())
        );
        Mission mission = Mission.builder().missionSpec(dto.missionSpec()).store(store)
                .deadline(dto.deadline()).build();
        missionRepository.save(mission);
    }

}
