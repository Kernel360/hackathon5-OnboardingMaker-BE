package com.example.onboarding.user.controller;

import com.example.onboarding.user.dto.*;
import com.example.onboarding.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody UserRequestDto dto) {
        userService.register(dto);
        return "회원가입 성공";
    }

    @PostMapping("/login")
    public UserResponseDto login(@RequestBody LoginRequestDto dto, HttpServletRequest request) {
        return userService.login(dto, request);
    }
}