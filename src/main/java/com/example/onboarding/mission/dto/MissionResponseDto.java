package com.example.onboarding.mission.dto;

import com.example.onboarding.mission.entity.Mission;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MissionResponseDto {

    private int missionId;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private int totalGroups;
    private LocalDateTime createdAt;

    public static MissionResponseDto fromEntity(Mission mission) {
        return MissionResponseDto.builder()
                .missionId(mission.getMissionId())
                .title(mission.getTitle())
                .description(mission.getDescription())
                .deadline(mission.getDeadline())
                .totalGroups(mission.getTotalGroups())
                .createdAt(mission.getCreatedAt())
                .build();
    }
}
