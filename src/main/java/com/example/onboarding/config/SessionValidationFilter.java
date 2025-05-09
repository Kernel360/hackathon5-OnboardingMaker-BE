package com.example.onboarding.config;

import com.example.onboarding.repository.UserSessionRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SessionValidationFilter extends OncePerRequestFilter {

    private final UserSessionRepository userSessionRepository;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        // 로그인, 회원가입, 로그아웃 등은 검사 불필요
        return "/api/user/login".equals(path)
                || "/api/user/register".equals(path)
                || "/logout".equals(path);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String sessionId = session.getId();
            // 세션 테이블에 없으면 무효 처리
            if (!userSessionRepository.existsBySessionId(sessionId)) {
                // Optionally: clear SecurityContext
                SecurityContextHolder.clearContext();
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid session");
                return;
            }
        }
        // 로그인 시도나 세션 없는 사용자도 여기는 그냥 통과
        filterChain.doFilter(request, response);
    }
}

