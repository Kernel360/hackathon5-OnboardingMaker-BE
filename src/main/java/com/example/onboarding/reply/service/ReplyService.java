package com.example.onboarding.reply.service;

import com.example.onboarding.reply.entity.Reply;
import com.example.onboarding.reply.model.ReplyRequest;
import com.example.onboarding.reply.model.ReplyResponse;
import com.example.onboarding.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;

    public Reply save(ReplyRequest request){
        Reply reply = Reply.builder()
                .userId(request.getUserId())
                .groupId(request.getGroupId())
                .content(request.getContent())
                .build();
        return replyRepository.save(reply);
    }

    public List<ReplyResponse> findAll() {
        return replyRepository.findAll().stream()
                .map(reply -> {
                    LocalDateTime finalTime = reply.getUpdatedAt() != null
                            ? reply.getUpdatedAt()
                            : reply.getCreatedAt();

                    return ReplyResponse.builder()
                            .replyId(reply.getReplyId())
                            .userId(reply.getUserId())
                            .groupId(reply.getGroupId())
                            .content(reply.getContent())
                            .finalTime(finalTime)
                            .build();
                })
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
}
