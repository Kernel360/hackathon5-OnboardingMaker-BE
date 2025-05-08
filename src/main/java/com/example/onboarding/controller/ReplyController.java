package com.example.onboarding.controller;

import com.example.onboarding.entity.Reply;
import com.example.onboarding.dto.ReplyRequest;
import com.example.onboarding.dto.ReplyResponse;
import com.example.onboarding.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping("")
    public ReplyResponse createReply(@RequestBody ReplyRequest request){
        return toDto(replyService.save(request));
    }

    @GetMapping("")
    public List<ReplyResponse> getAllReplies(){
        return replyService.findAll();
    }

    @PutMapping("/{replyId}")
    public ReplyResponse updateReply(@PathVariable("replyId") int replyId, @RequestBody ReplyRequest request) {
        return toDto(replyService.update(replyId, request));
    }

    @DeleteMapping("/{replyId}")
    public void deleteReply(@PathVariable("replyId") int replyId) {
        replyService.deleteById(replyId);
    }

    // 대댓글
    @PostMapping("/{parentId}/nested")
    public ReplyResponse createNestedReply(@PathVariable Long parentId,
                                   @RequestBody ReplyRequest request) {
        return toDto(replyService.saveNestedReply(parentId, request));
    }

    @GetMapping("/{parentId}/nested")
    public List<ReplyResponse> getNestedReplies(@PathVariable Long parentId) {
        return replyService.findNestedReplies(parentId);
    }

    private ReplyResponse toDto(Reply e) {
        return ReplyResponse.builder()
                .replyId(e.getReplyId())
                .userId(e.getUser().getUserId())
                .groupId(e.getGroup().getGroupId())
                .content(e.getContent())
                .finalTime(
                        e.getUpdatedAt() != null
                                ? e.getUpdatedAt()
                                : e.getCreatedAt()
                )
                .parentReplyId(
                        e.getParentReply() != null
                                ? e.getParentReply().getReplyId()
                                : null
                )
                .build();
    }
}