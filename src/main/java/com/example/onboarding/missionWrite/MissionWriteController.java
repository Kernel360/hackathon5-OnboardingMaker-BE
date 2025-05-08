package com.example.onboarding.missionWrite;

import com.example.onboarding.OnboardingApplication;
import com.example.onboarding.global.exception.GlobalExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/missionWrite")
public class MissionWriteController {

    private final GlobalExceptionHandler globalExceptionHandler;

    private final OnboardingApplication onboardingApplication;

	@Autowired
	MissionWriteService missionWriteService;

    MissionWriteController(OnboardingApplication onboardingApplication, GlobalExceptionHandler globalExceptionHandler) {
        this.onboardingApplication = onboardingApplication;
        this.globalExceptionHandler = globalExceptionHandler;
    }

	@PostMapping
	public ResponseEntity<MissionWriteDTO> postWrite(@RequestBody MissionWriteDTO dto) {
		
		missionWriteService.createMission(dto);
		
		System.out.println(dto.getCreatedAt());
		System.out.println(dto.getDeadline());
		System.out.println(dto.getDescription());
		System.out.println(dto.getMissionId());
		System.out.println(dto.getTitle());
		System.out.println(dto.getTotalGroups());
		
		// TODO try~catch로 예외처리 하기

		return new ResponseEntity<>(dto, HttpStatus.OK);
		
	}

}