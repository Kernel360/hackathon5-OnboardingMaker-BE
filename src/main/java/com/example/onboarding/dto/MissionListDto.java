package com.example.onboarding.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MissionListDto {
    private String title;
    private String description;
    private LocalDateTime deadlWine;
    private Integer missionId;

}
