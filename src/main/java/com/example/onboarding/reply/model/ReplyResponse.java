package com.example.onboarding.reply.model;

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
    private int replyId;
    private int userId;
    private int groupId;
    private String content;
    private LocalDateTime finalTime;
}
