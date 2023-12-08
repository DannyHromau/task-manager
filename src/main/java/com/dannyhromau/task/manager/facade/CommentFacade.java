package com.dannyhromau.task.manager.facade;

import com.dannyhromau.task.manager.api.dto.CommentDto;
import com.dannyhromau.task.manager.core.base.BaseFacade;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CommentFacade extends BaseFacade<CommentDto> {
    @Override
    List<CommentDto> getDtos(Pageable pageable);

    @Override
    CommentDto getDtoByID(UUID id);

    @Override
    CommentDto addDto(CommentDto dto);

    @Override
    UUID deleteDtoById(UUID id);

    List<CommentDto> getCommentsByTaskId(Pageable pageable, UUID taskId);

    @Override
    CommentDto updateDto(CommentDto dto);

    List<CommentDto> getCommentsByAuthorId(Pageable pageable, UUID authorId);

}
