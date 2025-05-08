package com.example.onboarding.reply.controller;

import com.example.onboarding.reply.entity.Reply;
import com.example.onboarding.reply.model.ReplyRequest;
import com.example.onboarding.reply.model.ReplyResponse;
import com.example.onboarding.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping("")
    public Reply createReply(@RequestBody ReplyRequest request){
        return replyService.save(request);
    }

    @GetMapping("")
    public List<ReplyResponse> getAllReplies(){
        return replyService.findAll();
    }

    @PutMapping("/{replyId}")
    public Reply updateReply(@PathVariable("replyId") int replyId, @RequestBody ReplyRequest request) {
        return replyService.update(replyId, request);
    }

    @DeleteMapping("/{replyId}")
    public void deleteReply(@PathVariable("replyId") int replyId) {
        replyService.deleteById(replyId);
    }
}