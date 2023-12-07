package com.dannyhromau.task.manager.mapper;

import com.dannyhromau.task.manager.api.dto.CommentDto;
import com.dannyhromau.task.manager.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TaskMapper.class, UserMapper.class})
public interface CommentMapper {
    Comment mapToComment(CommentDto commentDto);
    CommentDto mapToCommentDto(Comment comment);
    void updateCommentFromDto(CommentDto commentDto, @MappingTarget Comment comment);
    List<CommentDto> mapToListCommentDto(List<Comment> commentList);
}
