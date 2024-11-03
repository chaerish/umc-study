package com.umc.study;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mission")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long missionId;

    @Column(length = 30, nullable = false)
    private String missionName;

    @Column(nullable = false)
    private Long missionPoint;

    @Column(nullable = false)
    private Boolean isCompleted;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

}
