package com.dannyhromau.task.manager.service;

import com.dannyhromau.task.manager.core.base.BaseService;
import com.dannyhromau.task.manager.model.Comment;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CommentService extends BaseService<Comment> {
    List<Comment> getEntitiesByAuthorId(Pageable pageable, UUID authorId);

    @Override
    List<Comment> getEntities(Pageable pageable);

    List<Comment> getEntitiesByTaskId(Pageable pageable, UUID taskId);

    @Override
    Comment getEntityById(UUID id);

    @Override
    Comment addEntity(Comment entity);

    @Override
    UUID deleteEntity(UUID id);

    @Override
    Comment updateEntity(Comment entity);

}
