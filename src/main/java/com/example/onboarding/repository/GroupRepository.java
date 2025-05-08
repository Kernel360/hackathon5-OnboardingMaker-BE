package com.example.onboarding.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onboarding.entity.GroupEntity;

public interface GroupRepository extends JpaRepository<GroupEntity, Integer> {

}
