package com.dannyhromau.task.manager.facade.impl;

import com.dannyhromau.task.manager.api.dto.CommentDto;
import com.dannyhromau.task.manager.facade.CommentFacade;
import com.dannyhromau.task.manager.mapper.CommentMapper;
import com.dannyhromau.task.manager.model.Comment;
import com.dannyhromau.task.manager.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CommentFacadeImpl implements CommentFacade {

    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @Override
    public List<CommentDto> getDtos(Pageable pageable) {
        return commentMapper.mapToListCommentDto(commentService.getEntities(pageable));
    }

    @Override
    public CommentDto getDtoByID(UUID id) {
        return commentMapper.mapToCommentDto(commentService.getEntityById(id));
    }

    @Override
    public CommentDto addDto(CommentDto dto) {
        Comment comment = commentMapper.mapToComment(dto);
        return commentMapper.mapToCommentDto(commentService.addEntity(comment));
    }

    @Override
    public UUID deleteDtoById(UUID id) {
        return commentService.deleteEntity(id);
    }

    @Override
    public List<CommentDto> getCommentsByAuthorId(Pageable pageable, UUID authorId) {
        return commentMapper.mapToListCommentDto(commentService.getEntitiesByAuthorId(pageable, authorId));
    }

    @Override
    public List<CommentDto> getCommentsByTaskId(Pageable pageable, UUID taskId) {
        return commentMapper.mapToListCommentDto(commentService.getEntitiesByTaskId(pageable, taskId));
    }

    @Override
    public CommentDto updateDto(CommentDto dto) {
        Comment comment = commentService.getEntityById(dto.getId());
        commentMapper.updateCommentFromDto(dto, comment);
        return commentMapper.mapToCommentDto(comment);
    }
}
