package com.umc.study.repository.review;

import com.umc.study.domain.Review;
import com.umc.study.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
