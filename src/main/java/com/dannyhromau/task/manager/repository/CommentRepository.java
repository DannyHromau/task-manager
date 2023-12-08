package com.dannyhromau.task.manager.repository;

import com.dannyhromau.task.manager.model.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findByAuthor_Id(Pageable pageable, UUID authorId);

    List<Comment> findByTask_Id(Pageable pageable, UUID taskId);
}
