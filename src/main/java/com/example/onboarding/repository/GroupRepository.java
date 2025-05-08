package com.example.onboarding.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onboarding.entity.GroupEntity;
import com.example.onboarding.entity.Mission;

public interface GroupRepository extends JpaRepository<GroupEntity, Integer> {

	List<GroupEntity> findByMissionId(Mission mission);

}
