package com.umc.study.repository.review;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc.study.domain.Member;
import com.umc.study.domain.QStore;
import com.umc.study.domain.Review;
import com.umc.study.domain.Store;
import com.umc.study.repository.store.StoreRepositoryCustom;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QReviewRepository implements ReviewRepositoryCustom {
    private final EntityManager entityManager;
    public Review createReview(String reviewTitle, String reviewContents, String reviewImg, float star, Long storeId, Member member) {
        Review review = Review.
                builder()
                .reviewTitle(reviewTitle)
                .reviewContents(reviewContents)
                .reviewImg(reviewImg)
                .reviewStar(star)
                .member(member)
                .build();
        entityManager.persist(review);
        return review;
    }

}
