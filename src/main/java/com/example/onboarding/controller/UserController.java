package com.example.onboarding.controller;

import com.example.onboarding.dto.LoginRequestDto;
import com.example.onboarding.dto.UserRequestDto;
import com.example.onboarding.dto.UserResponseDto;
import com.example.onboarding.service.UserService;
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
