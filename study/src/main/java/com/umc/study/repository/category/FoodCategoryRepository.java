package com.umc.study.repository.category;

import org.springframework.data.jpa.repository.JpaRepository;
import com.umc.study.domain.FoodCategory;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}
