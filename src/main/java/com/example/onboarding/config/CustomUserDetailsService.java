package com.example.onboarding.config;

import com.example.onboarding.entity.User;
import com.example.onboarding.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("[DEBUG] CustomUserDetailsService 진입 - email: " + email);

        return userRepository.findByEmail(email)
                .map(user -> {
                    System.out.println("[DEBUG] 사용자 찾음: " + user.getEmail());
                    System.out.println("[DEBUG] 사용자 비밀번호 (bcrypt): " + user.getPassword());

                    System.out.println("[DEBUG] 사용자 비밀번호 (bcrypt): " + user.getPassword());

                    return new CustomUserDetails(user);
                })
                .orElseThrow(() -> {
                    System.out.println("[DEBUG] 사용자 없음: " + email);
                    return new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
                });
    }
}
