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