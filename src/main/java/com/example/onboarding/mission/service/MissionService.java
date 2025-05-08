package com.example.onboarding.mission.service;

import com.example.onboarding.mission.dto.MissionListDto;
import com.example.onboarding.mission.dto.MissionResponseDto;
import com.example.onboarding.mission.entity.Mission;
import com.example.onboarding.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    public List<MissionListDto> getAllMissions() {
        return missionRepository.findTitleAndDeadline();
    }

    public MissionResponseDto getMissionById(Integer id) {
        Mission mission = missionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 미션이 없습니다."));
        return MissionResponseDto.fromEntity(mission);
    }
}
