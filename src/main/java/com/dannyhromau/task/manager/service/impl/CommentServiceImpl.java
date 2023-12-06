package com.dannyhromau.task.manager.service.impl;

import com.dannyhromau.task.manager.model.Comment;
import com.dannyhromau.task.manager.service.CommentService;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public class CommentServiceImpl implements CommentService {
    @Override
    public List<Comment> getEntities(Pageable pageable) {
        return null;
    }

    @Override
    public Comment getEntityById(UUID id) {
        return null;
    }

    @Override
    public Comment addEntity(Comment entity) {
        return null;
    }

    @Override
    public UUID deleteEntity(UUID id) {
        return null;
    }

    @Override
    public Comment updateEntity(Comment entity) {
        return null;
    }
}
