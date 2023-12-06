package com.dannyhromau.task.manager.repository;

import com.dannyhromau.task.manager.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
}
