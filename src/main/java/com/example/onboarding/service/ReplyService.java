package com.example.onboarding.service;

import com.example.onboarding.entity.GroupEntity;
import com.example.onboarding.entity.Reply;
import com.example.onboarding.dto.ReplyRequest;
import com.example.onboarding.dto.ReplyResponse;
import com.example.onboarding.entity.User;
import com.example.onboarding.repository.GroupRepository;
import com.example.onboarding.repository.ReplyRepository;
import com.example.onboarding.repository.UserRepository;
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
        return replyRepository.save(reply);
    }

    public List<ReplyResponse> findAll(int groupID) {
        return replyRepository.findAllByGroup_GroupId(groupID).stream()
                .map(reply -> {
                    LocalDateTime finalTime = reply.getUpdatedAt() != null
                            ? reply.getUpdatedAt()
                            : reply.getCreatedAt();

                    return ReplyResponse.builder()
                            .replyId(reply.getReplyId())
                            .userId(reply.getUser().getUserId())
                            .groupId(reply.getGroup().getGroupId())
                            .content(reply.getContent())
                            .finalTime(finalTime)
                            .build();
                })
                .collect(Collectors.toList());
    }

    public Reply update(long replyId, ReplyRequest request){
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new RuntimeException("Reply not found with id: " + replyId));

        reply.setContent(request.getContent());
        return replyRepository.save(reply);
    }

    public void deleteById(long replyId) {
        replyRepository.deleteById(replyId);
    }

    // 대댓글
    public Reply saveNestedReply(Long parentId, ReplyRequest request) {
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

    public List<ReplyResponse> findNestedReplies(Long parentId) {
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


}
