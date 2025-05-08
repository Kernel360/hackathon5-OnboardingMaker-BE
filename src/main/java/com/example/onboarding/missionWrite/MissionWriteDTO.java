package com.example.onboarding.missionWrite;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MissionWriteDTO {
	
	// mission_id
	private int missionId;
	// total_groups
	private int totalGroups;
	// created_at 만든날짜
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private String createdAt;
	// deadline 마감날짜
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private String deadline;
	// title
	private String title;
	// description
	private String description;
	
	
}
