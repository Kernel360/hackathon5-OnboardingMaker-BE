package com.example.onboarding.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_session")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class UserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "session_id", nullable = false, unique = true)
    private String sessionId;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "expired_at")
    private LocalDateTime expiredAt;
}
