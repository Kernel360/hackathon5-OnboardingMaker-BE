//package com.example.onboarding.controller;
//
//import com.example.onboarding.dto.ReplyRequest;
//import com.example.onboarding.entity.Reply;
//import com.example.onboarding.entity.User;
//import com.example.onboarding.entity.GroupEntity;  // 실제 클래스명이 다르면 여기를 바꿔주세요
//import com.example.onboarding.service.ReplyService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDateTime;
//import java.util.Collections;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//
//@WebMvcTest(ReplyController.class)
//public class ReplyControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private ReplyService replyService;
//
//    @Test
//    @DisplayName("POST /api/reply - 성공 시 201, ReplyResponse 반환")
//    void createReply_success() throws Exception {
//        // given: 요청 DTO 준비
//        ReplyRequest req = new ReplyRequest();
//        req.setUserId(1);
//        req.setGroupId(2);
//        req.setContent("테스트댓글");
//
//        // given: 서비스가 반환할 엔티티(Mock)
//        Reply saved = Reply.builder()
//                .replyId(1L)
//                .user(User.builder().userId(1).build())
//                .group(GroupEntity.builder().groupId(2).build())
//                .content("테스트댓글")
//                .createdAt(LocalDateTime.now())
//                .childReplies(Collections.emptyList())
//                .build();
//
//        given(replyService.save(any(ReplyRequest.class)))
//                .willReturn(saved);
//
//        // when & then
//        mockMvc.perform(post("/api/reply")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(req)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.replyId").value(1))
//                .andExpect(jsonPath("$.content").value("테스트댓글"));
//    }
//
//    @Test
//    @DisplayName("POST /api/reply - 서비스 예외 시 500 반환")
//    void createReply_failure() throws Exception {
//        // given: 서비스에서 예외 발생
//        given(replyService.save(any(ReplyRequest.class)))
//                .willThrow(new RuntimeException("DB 오류"));
//
//        // when & then
//        mockMvc.perform(post("/api/reply")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{}"))
//                .andExpect(status().isInternalServerError())
//                .andExpect(jsonPath("$.status").value(500))
//                .andExpect(jsonPath("$.message").value("서버 내부 오류가 발생했습니다."));
//    }
//}
