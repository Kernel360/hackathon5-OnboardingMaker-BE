package com.example.onboarding.mission.dto;

import com.example.onboarding.mission.entity.Mission;
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
    private LocalDateTime deadline;

}
