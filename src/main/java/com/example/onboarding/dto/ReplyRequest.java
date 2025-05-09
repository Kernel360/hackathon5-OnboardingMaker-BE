package com.example.onboarding.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyRequest {
    private int userId;
    private int groupId;
    private String content;
    private int parentReplyId;
}
