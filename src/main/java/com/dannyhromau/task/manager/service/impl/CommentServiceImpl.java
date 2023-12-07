package com.dannyhromau.task.manager.service.impl;

import com.dannyhromau.task.manager.core.util.ErrorMessages;
import com.dannyhromau.task.manager.exception.EntityNotfoundException;
import com.dannyhromau.task.manager.exception.InvalidDataException;
import com.dannyhromau.task.manager.model.Comment;
import com.dannyhromau.task.manager.repository.CommentRepository;
import com.dannyhromau.task.manager.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private static final String ENTITY_NOT_FOUND_MESSAGE = ErrorMessages.ENTITY_NOT_FOUND_MESSAGE.label;
    private static final String NULLABLE_ID_MESSAGE = ErrorMessages.NULLABLE_ID_MESSAGE.label;

    @Override
    public List<Comment> getEntities(Pageable pageable) {
        return commentRepository.findAll(pageable).toList();
    }

    @Override
    public Comment getEntityById(UUID id) {
        return checkValidData(id);
    }

    @Override
    public Comment addEntity(Comment comment) {
       return commentRepository.save(comment);
    }

    @Override
    public UUID deleteEntity(UUID id) {
        commentRepository.deleteById(checkValidData(id).getId());
        return id;
    }

    @Override
    public Comment updateEntity(Comment comment) {
        return commentRepository.save(comment);
    }

    private Comment checkValidData(UUID id) {
        if (id == null) {
            throw new InvalidDataException(NULLABLE_ID_MESSAGE);
        }
        Optional<Comment> userOpt = commentRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new EntityNotfoundException(String.format(ENTITY_NOT_FOUND_MESSAGE, id));
        } else {
            return userOpt.get();
        }
    }
}
