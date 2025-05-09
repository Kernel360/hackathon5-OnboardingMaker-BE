package com.example.onboarding.repository;

import com.example.onboarding.entity.UserSession;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSessionRepository extends JpaRepository<UserSession, Long> {
    @Transactional
    void deleteBySessionId(String sessionId);
    boolean existsBySessionId(String sessionId);
}
