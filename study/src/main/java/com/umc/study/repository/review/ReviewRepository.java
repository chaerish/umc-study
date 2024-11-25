package com.umc.study.repository.review;

import com.umc.study.domain.Member;
import com.umc.study.domain.Review;
import com.umc.study.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    Page<Review> findAllByStore(Store store, PageRequest pageRequest);
    Page<Review> findAllByStoreAndMember(Store store, Member member, Pageable pageable);
}
