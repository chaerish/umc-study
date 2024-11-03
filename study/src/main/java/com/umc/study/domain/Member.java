package com.umc.study.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length = 10, nullable = false)
    private String userName;

    @Column
    private LocalDateTime userBirth;

    @Column
    private String userAddress;

    @Column
    private Long userPoints;

    @Column(unique = true, nullable = false)
    private String userEmail;
    @OneToMany(mappedBy = "users")
    private List<FavoriteFood> favoriteFoods = new ArrayList<>();
    @OneToMany(mappedBy = "users")
    private List<Review> reviews = new ArrayList<>();
    @OneToMany(mappedBy = "users")
    private List<Mission> missions = new ArrayList<>();
}
