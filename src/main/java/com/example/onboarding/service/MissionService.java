package com.example.onboarding.service;

import com.example.onboarding.dto.MissionListDto;
import com.example.onboarding.dto.MissionResponseDto;
import com.example.onboarding.dto.MissionTeamDto;
import com.example.onboarding.entity.GroupEntity;
import com.example.onboarding.entity.Mission;
import com.example.onboarding.repository.GroupRepository;
import com.example.onboarding.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final GroupRepository groupRepository;

    public List<MissionListDto> getAllMissions() {
        return missionRepository.findTitleAndDeadline();
    }

    public MissionResponseDto getMissionById(Integer id) {
        Mission mission = missionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 미션이 없습니다."));
        return MissionResponseDto.fromEntity(mission);
    }

    public MissionTeamDto getMissionTitleAndTeamCount(Integer missionId) {
        Mission mission = missionRepository.findById(missionId)
            .orElseThrow(() -> new RuntimeException("해당 미션이 존재하지 않습니다."));

        return new MissionTeamDto(mission.getTitle(), mission.getTotalGroups());
    }
    
}