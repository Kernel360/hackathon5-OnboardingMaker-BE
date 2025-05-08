package com.example.onboarding.missionWrite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onboarding.missionWrite.Entity.GroupEntity;

public interface GroupRepository extends JpaRepository<GroupEntity, Integer> {

}
