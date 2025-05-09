package com.example.onboarding.repository;

import com.example.onboarding.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    List<Reply> findAllByGroup_GroupId(int groupGroupId);
    List<Reply> findByParentReply_ReplyIdOrderByCreatedAtAsc(int parentId);
}
