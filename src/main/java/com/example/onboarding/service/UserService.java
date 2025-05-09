package com.example.onboarding.service;

import com.example.onboarding.dto.UserRequestDto;
import com.example.onboarding.entity.User;
import com.example.onboarding.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(UserRequestDto dto) {
        // 이메일 중복 검사
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }
        // 비밀번호 암호화 후 사용자 엔티티 생성 및 저장
        User user = User.builder()
                .nickname(dto.getNickname())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .isAdmin(dto.isAdmin())
                .build();
        userRepository.save(user);
    }
}
