package com.example.onboarding.service;

import com.example.onboarding.entity.GroupEntity;
import com.example.onboarding.entity.Reply;
import com.example.onboarding.dto.ReplyRequest;
import com.example.onboarding.dto.ReplyResponse;
import com.example.onboarding.entity.User;
import com.example.onboarding.repository.GroupRepository;
import com.example.onboarding.repository.ReplyRepository;
import com.example.onboarding.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public Reply save(ReplyRequest request){
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found: " + request.getUserId()));
        GroupEntity group = groupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new RuntimeException("Group not found: " + request.getGroupId()));

        Reply reply = Reply.builder()
                .user(user)
                .group(group)
                .content(request.getContent())
                .build();
        return replyRepository.saveAndFlush(reply);
    }

    @Transactional(readOnly = true)
    public List<ReplyResponse> findAll(int groupID) {
        Set<Integer> visited = new HashSet<>();
        return replyRepository.findAllByGroup_GroupId(groupID).stream()
                // 최상위(부모) 댓글만 필터링
                .filter(reply -> reply.getParentReply() == null)
                // 재귀 매핑
                .map(reply -> toDto(reply, visited))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        }

    public Reply update(int replyId, ReplyRequest request){
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new RuntimeException("Reply not found with id: " + replyId));

        reply.setContent(request.getContent());
        return replyRepository.save(reply);
    }

    public void deleteById(int replyId) {
        replyRepository.deleteById(replyId);
    }

    // 대댓글
    public Reply saveNestedReply(Integer parentId, ReplyRequest request) {
        Reply parent = replyRepository.findById(parentId)
                .orElseThrow(() -> new RuntimeException("Parent reply not found: " + parentId));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found: " + request.getUserId()));
        GroupEntity group = groupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new RuntimeException("Group not found: " + request.getGroupId()));

        Reply nested = Reply.builder()
                .user(user)
                .group(group)
                .content(request.getContent())
                .parentReply(parent)
                .build();

        return replyRepository.save(nested);
    }

    public List<ReplyResponse> findNestedReplies(Integer parentId) {
        return replyRepository.findByParentReply_ReplyIdOrderByCreatedAtAsc(parentId).stream()
                .map(child -> {
                    LocalDateTime finalTime = child.getUpdatedAt() != null
                            ? child.getUpdatedAt()
                            : child.getCreatedAt();
                    return ReplyResponse.builder()
                            .replyId(child.getReplyId())
                            .userId(child.getUser().getUserId())
                            .groupId(child.getGroup().getGroupId())
                            .content(child.getContent())
                            .finalTime(finalTime)
                            .build();
                })
                .collect(Collectors.toList());
    }

    private ReplyResponse toDto(Reply e, Set<Integer> visited) {
        // 이미 방문한 댓글이면 순환 방지
        if (!visited.add(e.getReplyId())) {
            return null;
        }

        LocalDateTime finalTime =
                e.getUpdatedAt() != null ? e.getUpdatedAt() : e.getCreatedAt();

        ReplyResponse dto = ReplyResponse.builder()
                .replyId(e.getReplyId())
                .userId(e.getUser().getUserId())
                .groupId(e.getGroup().getGroupId())
                .content(e.getContent())
                .finalTime(finalTime)
                .childReplies(new ArrayList<>())
                .build();

        // 자식 댓글들 재귀 매핑
        for (Reply child : e.getChildReplies()) {
            ReplyResponse childDto = toDto(child, visited);
            if (childDto != null) {
                dto.getChildReplies().add(childDto);
            }
        }

        // 매핑 끝나면 방문 목록에서 제거
        visited.remove(e.getReplyId());
        return dto;
    }



}
