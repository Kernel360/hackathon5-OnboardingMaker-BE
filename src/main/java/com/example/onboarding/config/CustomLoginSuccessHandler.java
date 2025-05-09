package com.example.onboarding.config;

import com.example.onboarding.entity.UserSession;
import com.example.onboarding.repository.UserSessionRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    private final UserSessionRepository userSessionRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        HttpSession session = request.getSession();
        String sessionId = session.getId();

        // CustomUserDetails에서 userId 꺼내기
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        int userId = userDetails.getUser().getUserId();

        UserSession userSession = UserSession.builder()
                .userId(userId)
                .sessionId(sessionId)
                .createdAt(LocalDateTime.now())
                .build();

        userSessionRepository.save(userSession);

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("로그인 성공");
        System.out.println("[DEBUG] 로그인 성공 핸들러 작동!");
    }
}
