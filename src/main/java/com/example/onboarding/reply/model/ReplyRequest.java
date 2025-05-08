package com.example.onboarding.reply.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyRequest {
    private int userId;
    private int groupId;
    private String content;
}
