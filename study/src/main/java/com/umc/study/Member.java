package com.umc.study;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user")
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
    private List<FavoriteFood> favoriteFoods;
    @OneToMany(mappedBy = "users")
    private List<Review> reviews;
    @OneToMany(mappedBy = "users")
    private List<Mission> missions;
}
