package com.example.onboarding.controller;

import com.example.onboarding.entity.Reply;
import com.example.onboarding.dto.ReplyRequest;
import com.example.onboarding.dto.ReplyResponse;
import com.example.onboarding.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reply")
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping("")
    public ReplyResponse createReply(@RequestBody ReplyRequest request){
        Set<Long> visited = new HashSet<>();
        return toDto(replyService.save(request), visited);
    }

    @GetMapping("/{groupID}")
    public List<ReplyResponse> getAllReplies(@PathVariable("groupID") int groupID){
        return replyService.findAll(groupID);
    }

    @PutMapping("/{replyId}")
    public ReplyResponse updateReply(@PathVariable("replyId") int replyId, @RequestBody ReplyRequest request) {
        Set<Long> visited = new HashSet<>();
        return toDto(replyService.update(replyId, request), visited);
    }

    @DeleteMapping("/{replyId}")
    public void deleteReply(@PathVariable("replyId") int replyId) {
        replyService.deleteById(replyId);
    }

    // 대댓글
    @PostMapping("/{parentId}/nested")
    public ReplyResponse createNestedReply(@PathVariable Long parentId,
                                   @RequestBody ReplyRequest request) {
        Set<Long> visited = new HashSet<>();
        return toDto(replyService.saveNestedReply(parentId, request), visited);
    }

    @GetMapping("/{parentId}/nested")
    public List<ReplyResponse> getNestedReplies(@PathVariable Long parentId) {
        return replyService.findNestedReplies(parentId);
    }

    private ReplyResponse toDto(Reply e, Set<Long> visited) {

        if (!visited.add(e.getReplyId())) {
            // 이미 매핑한 댓글이면 재귀 중단
            return null;
        }

        LocalDateTime utcTime = Optional.ofNullable(e.getUpdatedAt()).orElse(e.getCreatedAt());
        ZonedDateTime seoulZoned = utcTime.atZone(ZoneOffset.UTC)
                .withZoneSameInstant(ZoneId.of("Asia/Seoul"));
        LocalDateTime seoulTime = seoulZoned.toLocalDateTime();

        ReplyResponse dto = ReplyResponse.builder()
                .replyId(e.getReplyId())
                .userId(e.getUser().getUserId())
                .groupId(e.getGroup().getGroupId())
                .content(e.getContent())
                .finalTime(seoulTime)
                .childReplies(new ArrayList<>())
                .build();

        List<ReplyResponse> children = e.getChildReplies().stream()
                .map(child -> toDto(child, visited))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        dto.setChildReplies(children);

        visited.remove(e.getReplyId());

        return dto;
    }
}
