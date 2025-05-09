package com.example.onboarding.config;

import com.example.onboarding.repository.UserSessionRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private final UserSessionRepository userSessionRepository;

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException {
        System.out.println("[DEBUG] 1. 로그아웃 핸들러 진입");

        // 1단계: 쿠키 확인
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            System.out.println("[DEBUG] 2. 요청에 쿠키 있음");
            for (Cookie c : cookies) {
                System.out.println("[DEBUG]   - 쿠키: " + c.getName() + " = " + c.getValue());
            }
        } else {
            System.out.println("[DEBUG] 2. 요청에 쿠키 없음");
        }

        // 2단계: 세션 확인
        HttpSession session = request.getSession(false);
        if (session == null) {
            System.out.println("[DEBUG] 3. 세션이 존재하지 않음 (null)");
        } else {
            System.out.println("[DEBUG] 3. 세션 객체 존재함");
            String sessionId = session.getId();
            System.out.println("[DEBUG] 4. 세션 ID: " + sessionId);

            // 3단계: DB에서 세션 삭제 시도
            try {
                userSessionRepository.deleteBySessionId(sessionId);
                System.out.println("[DEBUG] 5. user_session 테이블에서 삭제 성공");
            } catch (Exception e) {
                System.out.println("[ERROR] 5. user_session 삭제 중 예외 발생: " + e.getMessage());
            }


            // 4단계: 세션 무효화
            session.invalidate();
            System.out.println("[DEBUG] 6. 세션 invalidate() 완료");
        }

        // 5단계: 응답 반환
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("로그아웃 성공");
        System.out.println("[DEBUG] 7. 로그아웃 응답 완료 (200 OK)");
    }
}
