package com.umc.study.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "store_category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeCategoryId;

    @Column(nullable = false)
    private String storeCategoryName;

    @OneToMany(mappedBy = "storeCategory", cascade = CascadeType.ALL)
    private List<Store> stores = new ArrayList<>();

}
