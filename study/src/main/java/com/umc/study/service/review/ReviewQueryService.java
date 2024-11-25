package com.umc.study.service.review;

import com.umc.study.domain.Member;
import com.umc.study.domain.Review;
import com.umc.study.domain.Store;
import com.umc.study.repository.member.MemberRepository;
import com.umc.study.repository.review.ReviewRepository;
import com.umc.study.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    public Page<Review> getMyReview(Integer page, Long storeId){
        Member member =memberRepository.findById(1L).get();
        Store store = storeRepository.findById(storeId).get();
        return reviewRepository.findAllByStoreAndMember(store,member,PageRequest.of(page,10));
    }

}
