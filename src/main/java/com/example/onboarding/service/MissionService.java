package com.example.onboarding.service;

import com.example.onboarding.dto.MissionListDto;
import com.example.onboarding.dto.MissionResponseDto;
import com.example.onboarding.entity.Mission;
import com.example.onboarding.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
