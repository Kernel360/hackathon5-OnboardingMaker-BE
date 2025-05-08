package com.example.onboarding.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyResponse {
    private long replyId;
    private int userId;
    private int groupId;
    private String content;
    private LocalDateTime finalTime;
    private Long parentReplyId;
}
