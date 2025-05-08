package com.example.onboarding.controller;

import com.example.onboarding.dto.MissionListDto;
import com.example.onboarding.dto.MissionResponseDto;
import com.example.onboarding.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mission")
public class MissionController {

    private final MissionService missionService;

    // 전체 미션 목록 조회
    @GetMapping("/list")
    public List<MissionListDto> getMissionName() {
        return missionService.getAllMissions();
    }

    // 개별 미션 상세 조회
    @GetMapping("/{id}")
    public MissionResponseDto getMissionDetail(@PathVariable Integer id) {
        return missionService.getMissionById(id);
    }
}
