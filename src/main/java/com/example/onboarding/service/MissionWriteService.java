package com.example.onboarding.service;

import java.time.LocalDateTime;

import com.example.onboarding.dto.MissionWriteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onboarding.entity.Mission;
import com.example.onboarding.repository.MissionRepository;
import com.example.onboarding.entity.GroupEntity;
import com.example.onboarding.repository.GroupRepository;

import jakarta.transaction.Transactional;

@Service
public class MissionWriteService {

	@Autowired
	MissionRepository missionRepository;

	@Autowired
	GroupRepository groupRepository;

	@Transactional
	public Mission createMission(MissionWriteDTO dto) {
		
		// Mission 글 저장
		Mission mission = new Mission();
		
		mission.setTitle(dto.getTitle());
		mission.setDescription(dto.getDescription());
		mission.setTotalGroups((int) dto.getTotalGroups());
		mission.setCreatedAt(LocalDateTime.parse(dto.getCreatedAt())); // TODO 현재 시간 받게 만들기
		mission.setDeadline(LocalDateTime.parse(dto.getDeadline()));
		
		Mission saved = missionRepository.save(mission);

		// total_groups 만큼 Group 생성
		int total = dto.getTotalGroups();  
		
	    for (int i = 1; i <= total; i++) {
	        GroupEntity g = new GroupEntity();
	        g.setMissionId(saved);
	        groupRepository.save(g);
	        
	    }
	    
	    return saved;
	    
	}

}
