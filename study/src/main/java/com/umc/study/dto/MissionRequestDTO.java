package com.umc.study.dto;

import java.time.LocalDate;

public record MissionRequestDTO(
        String missionSpec,
        Integer reward,
        LocalDate deadline
) {
}
